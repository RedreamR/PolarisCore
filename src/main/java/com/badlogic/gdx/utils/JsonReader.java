// Decompiled with: Procyon 0.6.0
// Class Version: 7
package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.fontFix.EncodingDetector;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;

public class JsonReader implements BaseJsonReader
{
    private static final byte[] _json_actions;
    private static final short[] _json_key_offsets;
    private static final char[] _json_trans_keys;
    private static final byte[] _json_single_lengths;
    private static final byte[] _json_range_lengths;
    private static final short[] _json_index_offsets;
    private static final byte[] _json_indicies;
    private static final byte[] _json_trans_targs;
    private static final byte[] _json_trans_actions;
    private static final byte[] _json_eof_actions;
    static final int json_start = 1;
    static final int json_first_final = 35;
    static final int json_error = 0;
    static final int json_en_object = 5;
    static final int json_en_array = 23;
    static final int json_en_main = 1;
    private final Array<JsonValue> elements;
    private final Array<JsonValue> lastChild;
    private JsonValue root;
    private JsonValue current;

    public JsonReader() {
        this.elements = new Array<JsonValue>(8);
        this.lastChild = new Array<JsonValue>(8);
    }

    public JsonValue parse(final String json) {
        final char[] data = json.toCharArray();
        return this.parse(data, 0, data.length);
    }

    public JsonValue parse(final Reader reader) {
        char[] data = new char[1024];
        int offset = 0;
        try {
            while (true) {
                final int length = reader.read(data, offset, data.length - offset);
                if (length == -1) {
                    break;
                }
                if (length == 0) {
                    final char[] newData = new char[data.length * 2];
                    System.arraycopy(data, 0, newData, 0, data.length);
                    data = newData;
                }
                else {
                    offset += length;
                }
            }
        }
        catch (final IOException ex) {
            throw new SerializationException("Error reading input.", ex);
        }
        finally {
            StreamUtils.closeQuietly(reader);
        }
        return this.parse(data, 0, offset);
    }

    @Override
    public JsonValue parse(final InputStream input) {
        Reader reader;
        String charset = null;
        try {
            charset = EncodingDetector.INSTANCE.detectInputStreamCharset(input);
            switch (charset) {
                case "GB18030":
                    charset = "GB18030";
                    break;
                case "BIG5":
                    charset = "Big5";
                    break;
                case "Shift_JIS":
                    charset = "Shift_JIS";
                    break;
                default:
                    charset = "UTF-8";
                    break;
            }
        }catch (NullPointerException ignored){
        } catch (Throwable throwable) {
            FinalityLogger.error("Error while detecting charset", throwable);
        }

        if(charset == null){
            charset = "UTF-8";
        }
        try {
            reader = new InputStreamReader(input, charset);
        }
        catch (final Exception ex) {
            throw new SerializationException("Error reading stream.", ex);
        }
        return this.parse(reader);
    }

    @Override
    public JsonValue parse(final FileHandle file) {
        Reader reader;
        String charset = "";
        String str = file.name();
        boolean isSkip = str.contains("ProvincePoint") || str.contains("ProvinceNeighboringProvinces");
        try {
            if(isSkip){
                charset = "UTF-8";
            }else {
                charset = EncodingDetector.INSTANCE.detectStringCharset(file);
                switch (charset) {
                    case "GB18030":
                        charset = "GB18030";
                        break;
                    case "BIG5":
                        charset = "Big5";
                        break;
                    case "Shift_JIS":
                        charset = "Shift_JIS";
                        break;
                    default:
                        charset = "UTF-8";
                        break;
                }
            }
        }catch (NullPointerException ignored){
        } catch (Throwable throwable) {
            FinalityLogger.error("Error while detecting charset", throwable);
        }

        if(charset == null){
            charset = "UTF-8";
        }
        try {
            reader = file.reader(charset);
        }
        catch (final Exception ex) {
            throw new SerializationException("Error reading file: " + file, ex);
        }
        try {
            return this.parse(reader);
        }
        catch (final Exception ex) {
            throw new SerializationException("Error parsing file: " + file, ex);
        }
    }

    public JsonValue parse(final char[] data, final int offset, final int length) {
        int p = offset;
        final int eof;
        final int pe = eof = length;
        int top = 0;
        int[] stack = new int[4];
        int s = 0;
        final Array<String> names = new Array<String>(8);
        boolean needsUnescape = false;
        boolean stringIsName = false;
        boolean stringIsUnquoted = false;
        RuntimeException parseRuntimeEx = null;
        final boolean debug = false;
        try {
            int cs = 1;
            top = 0;
            int _trans = 0;
            int _goto_targ = 0;
            Label_2931: {
                Label_0070:
                while (true) {
                    while (true) {
                        switch (_goto_targ) {
                            case 0: {
                                if (p == pe) {
                                    _goto_targ = 4;
                                    continue;
                                }
                                if (cs == 0) {
                                    _goto_targ = 5;
                                    continue;
                                }
                            }
                            case 1: {
                                int _keys = JsonReader._json_key_offsets[cs];
                                _trans = JsonReader._json_index_offsets[cs];
                                int _klen = JsonReader._json_single_lengths[cs];
                                Label_0392: {
                                    if (_klen > 0) {
                                        int _lower = _keys;
                                        int _upper = _keys + _klen - 1;
                                        while (_upper >= _lower) {
                                            final int _mid = _lower + (_upper - _lower >> 1);
                                            if (data[p] < JsonReader._json_trans_keys[_mid]) {
                                                _upper = _mid - 1;
                                            }
                                            else {
                                                if (data[p] <= JsonReader._json_trans_keys[_mid]) {
                                                    _trans += _mid - _keys;
                                                    break Label_0392;
                                                }
                                                _lower = _mid + 1;
                                            }
                                        }
                                        _keys += _klen;
                                        _trans += _klen;
                                    }
                                    _klen = JsonReader._json_range_lengths[cs];
                                    if (_klen > 0) {
                                        int _lower = _keys;
                                        int _upper = _keys + (_klen << 1) - 2;
                                        while (_upper >= _lower) {
                                            final int _mid = _lower + (_upper - _lower >> 1 & 0xFFFFFFFE);
                                            if (data[p] < JsonReader._json_trans_keys[_mid]) {
                                                _upper = _mid - 2;
                                            }
                                            else {
                                                if (data[p] <= JsonReader._json_trans_keys[_mid + 1]) {
                                                    _trans += _mid - _keys >> 1;
                                                    break Label_0392;
                                                }
                                                _lower = _mid + 2;
                                            }
                                        }
                                        _trans += _klen;
                                    }
                                }
                                _trans = JsonReader._json_indicies[_trans];
                                cs = JsonReader._json_trans_targs[_trans];
                                if (JsonReader._json_trans_actions[_trans] != 0) {
                                    int _acts = JsonReader._json_trans_actions[_trans];
                                    int _nacts = JsonReader._json_actions[_acts++];
                                    while (_nacts-- > 0) {
                                        switch (JsonReader._json_actions[_acts++]) {
                                            case 0: {
                                                stringIsName = true;
                                                continue;
                                            }
                                            case 1: {
                                                String value = new String(data, s, p - s);
                                                if (needsUnescape) {
                                                    value = this.unescape(value);
                                                }
                                                Label_1233: {
                                                    if (stringIsName) {
                                                        stringIsName = false;
                                                        if (debug) {
                                                            System.out.println("name: " + value);
                                                        }
                                                        names.add(value);
                                                    }
                                                    else {
                                                        final String name = (names.size > 0) ? names.pop() : null;
                                                        Label_1184: {
                                                            if (stringIsUnquoted) {
                                                                if (value.equals("true")) {
                                                                    if (debug) {
                                                                        System.out.println("boolean: " + name + "=true");
                                                                    }
                                                                    this.bool(name, true);
                                                                    break Label_1233;
                                                                }
                                                                if (value.equals("false")) {
                                                                    if (debug) {
                                                                        System.out.println("boolean: " + name + "=false");
                                                                    }
                                                                    this.bool(name, false);
                                                                    break Label_1233;
                                                                }
                                                                if (value.equals("null")) {
                                                                    this.string(name, null);
                                                                    break Label_1233;
                                                                }
                                                                boolean couldBeDouble = false;
                                                                boolean couldBeLong = true;
                                                                Label_1047:
                                                                for (int i = s; i < p; ++i) {
                                                                    switch (data[i]) {
                                                                        case '+':
                                                                        case '-':
                                                                        case '0':
                                                                        case '1':
                                                                        case '2':
                                                                        case '3':
                                                                        case '4':
                                                                        case '5':
                                                                        case '6':
                                                                        case '7':
                                                                        case '8':
                                                                        case '9': {
                                                                            break;
                                                                        }
                                                                        case '.':
                                                                        case 'E':
                                                                        case 'e': {
                                                                            couldBeDouble = true;
                                                                            couldBeLong = false;
                                                                            break;
                                                                        }
                                                                        default: {
                                                                            couldBeDouble = false;
                                                                            couldBeLong = false;
                                                                            break Label_1047;
                                                                        }
                                                                    }
                                                                }
                                                                if (couldBeDouble) {
                                                                    try {
                                                                        if (debug) {
                                                                            System.out.println("double: " + name + "=" + Double.parseDouble(value));
                                                                        }
                                                                        this.number(name, Double.parseDouble(value), value);
                                                                        break Label_1233;
                                                                    }
                                                                    catch (final NumberFormatException ex2) {
                                                                        break Label_1184;
                                                                    }
                                                                }
                                                                if (couldBeLong) {
                                                                    if (debug) {
                                                                        System.out.println("double: " + name + "=" + Double.parseDouble(value));
                                                                    }
                                                                    try {
                                                                        this.number(name, Long.parseLong(value), value);
                                                                        break Label_1233;
                                                                    }
                                                                    catch (final NumberFormatException ex3) {}
                                                                }
                                                            }
                                                        }
                                                        if (debug) {
                                                            System.out.println("string: " + name + "=" + value);
                                                        }
                                                        this.string(name, value);
                                                    }
                                                }
                                                stringIsUnquoted = false;
                                                s = p;
                                                continue;
                                            }
                                            case 2: {
                                                final String name2 = (names.size > 0) ? names.pop() : null;
                                                if (debug) {
                                                    System.out.println("startObject: " + name2);
                                                }
                                                this.startObject(name2);
                                                if (top == stack.length) {
                                                    final int[] newStack = new int[stack.length * 2];
                                                    System.arraycopy(stack, 0, newStack, 0, stack.length);
                                                    stack = newStack;
                                                }
                                                stack[top++] = cs;
                                                cs = 5;
                                                _goto_targ = 2;
                                                continue Label_0070;
                                            }
                                            case 3: {
                                                if (debug) {
                                                    System.out.println("endObject");
                                                }
                                                this.pop();
                                                cs = stack[--top];
                                                _goto_targ = 2;
                                                continue Label_0070;
                                            }
                                            case 4: {
                                                final String name2 = (names.size > 0) ? names.pop() : null;
                                                if (debug) {
                                                    System.out.println("startArray: " + name2);
                                                }
                                                this.startArray(name2);
                                                if (top == stack.length) {
                                                    final int[] newStack = new int[stack.length * 2];
                                                    System.arraycopy(stack, 0, newStack, 0, stack.length);
                                                    stack = newStack;
                                                }
                                                stack[top++] = cs;
                                                cs = 23;
                                                _goto_targ = 2;
                                                continue Label_0070;
                                            }
                                            case 5: {
                                                if (debug) {
                                                    System.out.println("endArray");
                                                }
                                                this.pop();
                                                cs = stack[--top];
                                                _goto_targ = 2;
                                                continue Label_0070;
                                            }
                                            case 6: {
                                                final int start = p - 1;
                                                if (data[p++] == '/') {
                                                    while (p != eof && data[p] != '\n') {
                                                        ++p;
                                                    }
                                                    --p;
                                                }
                                                else {
                                                    while ((p + 1 < eof && data[p] != '*') || data[p + 1] != '/') {
                                                        ++p;
                                                    }
                                                    ++p;
                                                }
                                                if (!debug) {
                                                    continue;
                                                }
                                                System.out.println("comment " + new String(data, start, p - start));
                                                continue;
                                            }
                                            case 7: {
                                                if (debug) {
                                                    System.out.println("unquotedChars");
                                                }
                                                s = p;
                                                needsUnescape = false;
                                                stringIsUnquoted = true;
                                                Label_2009: {
                                                    if (stringIsName) {
                                                        do {
                                                            switch (data[p]) {
                                                                case '\\': {
                                                                    needsUnescape = true;
                                                                    break;
                                                                }
                                                                case '/': {
                                                                    if (p + 1 == eof) {
                                                                        break;
                                                                    }
                                                                    final char c = data[p + 1];
                                                                    if (c == '/') {
                                                                        break Label_2009;
                                                                    }
                                                                    if (c == '*') {
                                                                        break Label_2009;
                                                                    }
                                                                    break;
                                                                }
                                                                case '\n':
                                                                case '\r':
                                                                case ':': {
                                                                    break Label_2009;
                                                                }
                                                            }
                                                            if (debug) {
                                                                System.out.println("unquotedChar (name): '" + data[p] + "'");
                                                            }
                                                        } while (++p != eof);
                                                    }
                                                    else {
                                                        do {
                                                            switch (data[p]) {
                                                                case '\\': {
                                                                    needsUnescape = true;
                                                                    break;
                                                                }
                                                                case '/': {
                                                                    if (p + 1 == eof) {
                                                                        break;
                                                                    }
                                                                    final char c = data[p + 1];
                                                                    if (c == '/') {
                                                                        break Label_2009;
                                                                    }
                                                                    if (c == '*') {
                                                                        break Label_2009;
                                                                    }
                                                                    break;
                                                                }
                                                                case '\n':
                                                                case '\r':
                                                                case ',':
                                                                case ']':
                                                                case '}': {
                                                                    break Label_2009;
                                                                }
                                                            }
                                                            if (debug) {
                                                                System.out.println("unquotedChar (value): '" + data[p] + "'");
                                                            }
                                                        } while (++p != eof);
                                                    }
                                                }
                                                --p;
                                                while (Character.isSpace(data[p])) {
                                                    --p;
                                                }
                                                continue;
                                            }
                                            case 8: {
                                                if (debug) {
                                                    System.out.println("quotedChars");
                                                }
                                                s = ++p;
                                                needsUnescape = false;
                                                Label_2105:
                                                do {
                                                    switch (data[p]) {
                                                        case '\"': {
                                                            break Label_2105;
                                                        }
                                                        default: {
                                                            continue;
                                                        }
                                                        case '\\': {
                                                            needsUnescape = true;
                                                            ++p;
                                                            continue;
                                                        }
                                                    }
                                                } while (++p != eof);
                                                --p;
                                                continue;
                                            }
                                        }
                                    }
                                }
                            }
                            case 2: {
                                if (cs == 0) {
                                    _goto_targ = 5;
                                    continue;
                                }
                                if (++p != pe) {
                                    _goto_targ = 1;
                                    continue;
                                }
                                break Label_0070;
                            }
                            case 4: {
                                break Label_0070;
                            }
                            default: {
                                break Label_2931;
                            }
                        }
                    }
                }
                if (p == eof) {
                    int __acts = JsonReader._json_eof_actions[cs];
                    int __nacts = JsonReader._json_actions[__acts++];
                    while (__nacts-- > 0) {
                        switch (JsonReader._json_actions[__acts++]) {
                            case 1: {
                                String value2 = new String(data, s, p - s);
                                if (needsUnescape) {
                                    value2 = this.unescape(value2);
                                }
                                Label_2921: {
                                    if (stringIsName) {
                                        stringIsName = false;
                                        if (debug) {
                                            System.out.println("name: " + value2);
                                        }
                                        names.add(value2);
                                    }
                                    else {
                                        final String name3 = (names.size > 0) ? names.pop() : null;
                                        Label_2872: {
                                            if (stringIsUnquoted) {
                                                if (value2.equals("true")) {
                                                    if (debug) {
                                                        System.out.println("boolean: " + name3 + "=true");
                                                    }
                                                    this.bool(name3, true);
                                                    break Label_2921;
                                                }
                                                if (value2.equals("false")) {
                                                    if (debug) {
                                                        System.out.println("boolean: " + name3 + "=false");
                                                    }
                                                    this.bool(name3, false);
                                                    break Label_2921;
                                                }
                                                if (value2.equals("null")) {
                                                    this.string(name3, null);
                                                    break Label_2921;
                                                }
                                                boolean couldBeDouble2 = false;
                                                boolean couldBeLong2 = true;
                                                Label_2735:
                                                for (int j = s; j < p; ++j) {
                                                    switch (data[j]) {
                                                        case '+':
                                                        case '-':
                                                        case '0':
                                                        case '1':
                                                        case '2':
                                                        case '3':
                                                        case '4':
                                                        case '5':
                                                        case '6':
                                                        case '7':
                                                        case '8':
                                                        case '9': {
                                                            break;
                                                        }
                                                        case '.':
                                                        case 'E':
                                                        case 'e': {
                                                            couldBeDouble2 = true;
                                                            couldBeLong2 = false;
                                                            break;
                                                        }
                                                        default: {
                                                            couldBeDouble2 = false;
                                                            couldBeLong2 = false;
                                                            break Label_2735;
                                                        }
                                                    }
                                                }
                                                if (couldBeDouble2) {
                                                    try {
                                                        if (debug) {
                                                            System.out.println("double: " + name3 + "=" + Double.parseDouble(value2));
                                                        }
                                                        this.number(name3, Double.parseDouble(value2), value2);
                                                        break Label_2921;
                                                    }
                                                    catch (final NumberFormatException ex4) {
                                                        break Label_2872;
                                                    }
                                                }
                                                if (couldBeLong2) {
                                                    if (debug) {
                                                        System.out.println("double: " + name3 + "=" + Double.parseDouble(value2));
                                                    }
                                                    try {
                                                        this.number(name3, Long.parseLong(value2), value2);
                                                        break Label_2921;
                                                    }
                                                    catch (final NumberFormatException ex5) {}
                                                }
                                            }
                                        }
                                        if (debug) {
                                            System.out.println("string: " + name3 + "=" + value2);
                                        }
                                        this.string(name3, value2);
                                    }
                                }
                                stringIsUnquoted = false;
                                s = p;
                                continue;
                            }
                        }
                    }
                }
            }
        }
        catch (final RuntimeException ex) {
            parseRuntimeEx = ex;
        }
        final JsonValue root = this.root;
        this.root = null;
        this.current = null;
        this.lastChild.clear();
        if (p < pe) {
            int lineNumber = 1;
            for (int k = 0; k < p; ++k) {
                if (data[k] == '\n') {
                    ++lineNumber;
                }
            }
            final int start2 = Math.max(0, p - 32);
            throw new SerializationException("Error parsing JSON on line " + lineNumber + " near: " + new String(data, start2, p - start2) + "*ERROR*" + new String(data, p, Math.min(64, pe - p)), parseRuntimeEx);
        }
        if (this.elements.size != 0) {
            final JsonValue element = this.elements.peek();
            this.elements.clear();
            if (element != null && element.isObject()) {
                throw new SerializationException("Error parsing JSON, unmatched brace.");
            }
            throw new SerializationException("Error parsing JSON, unmatched bracket.");
        }
        else {
            if (parseRuntimeEx != null) {
                throw new SerializationException("Error parsing JSON: " + new String(data), parseRuntimeEx);
            }
            return root;
        }
    }

    private static byte[] init__json_actions_0() {
        return new byte[] { 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 2, 0, 7, 2, 0, 8, 2, 1, 3, 2, 1, 5 };
    }

    private static short[] init__json_key_offsets_0() {
        return new short[] { 0, 0, 11, 13, 14, 16, 25, 31, 37, 39, 50, 57, 64, 73, 74, 83, 85, 87, 96, 98, 100, 101, 103, 105, 116, 123, 130, 141, 142, 153, 155, 157, 168, 170, 172, 174, 179, 184, 184 };
    }

    private static char[] init__json_trans_keys_0() {
        return new char[] { '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '*', '/', '\"', '*', '/', '\r', ' ', '\"', ',', '/', ':', '}', '\t', '\n', '\r', ' ', '/', ':', '\t', '\n', '\r', ' ', '/', ':', '\t', '\n', '*', '/', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '\t', '\n', '\r', ' ', ',', '/', '}', '\t', '\n', '\r', ' ', ',', '/', '}', '\r', ' ', '\"', ',', '/', ':', '}', '\t', '\n', '\"', '\r', ' ', '\"', ',', '/', ':', '}', '\t', '\n', '*', '/', '*', '/', '\r', ' ', '\"', ',', '/', ':', '}', '\t', '\n', '*', '/', '*', '/', '\"', '*', '/', '*', '/', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '\t', '\n', '\r', ' ', ',', '/', ']', '\t', '\n', '\r', ' ', ',', '/', ']', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '\"', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '*', '/', '*', '/', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '*', '/', '*', '/', '*', '/', '\r', ' ', '/', '\t', '\n', '\r', ' ', '/', '\t', '\n', '\0' };
    }

    private static byte[] init__json_single_lengths_0() {
        return new byte[] { 0, 9, 2, 1, 2, 7, 4, 4, 2, 9, 7, 7, 7, 1, 7, 2, 2, 7, 2, 2, 1, 2, 2, 9, 7, 7, 9, 1, 9, 2, 2, 9, 2, 2, 2, 3, 3, 0, 0 };
    }

    private static byte[] init__json_range_lengths_0() {
        return new byte[] { 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 };
    }

    private static short[] init__json_index_offsets_0() {
        return new short[] { 0, 0, 11, 14, 16, 19, 28, 34, 40, 43, 54, 62, 70, 79, 81, 90, 93, 96, 105, 108, 111, 113, 116, 119, 130, 138, 146, 157, 159, 170, 173, 176, 187, 190, 193, 196, 201, 206, 207 };
    }

    private static byte[] init__json_indicies_0() {
        return new byte[] { 1, 1, 2, 3, 4, 3, 5, 3, 6, 1, 0, 7, 7, 3, 8, 3, 9, 9, 3, 11, 11, 12, 13, 14, 3, 15, 11, 10, 16, 16, 17, 18, 16, 3, 19, 19, 20, 21, 19, 3, 22, 22, 3, 21, 21, 24, 3, 25, 3, 26, 3, 27, 21, 23, 28, 29, 29, 28, 30, 31, 32, 3, 33, 34, 34, 33, 13, 35, 15, 3, 34, 34, 12, 36, 37, 3, 15, 34, 10, 16, 3, 36, 36, 12, 3, 38, 3, 3, 36, 10, 39, 39, 3, 40, 40, 3, 13, 13, 12, 3, 41, 3, 15, 13, 10, 42, 42, 3, 43, 43, 3, 28, 3, 44, 44, 3, 45, 45, 3, 47, 47, 48, 49, 50, 3, 51, 52, 53, 47, 46, 54, 55, 55, 54, 56, 57, 58, 3, 59, 60, 60, 59, 49, 61, 52, 3, 60, 60, 48, 62, 63, 3, 51, 52, 53, 60, 46, 54, 3, 62, 62, 48, 3, 64, 3, 51, 3, 53, 62, 46, 65, 65, 3, 66, 66, 3, 49, 49, 48, 3, 67, 3, 51, 52, 53, 49, 46, 68, 68, 3, 69, 69, 3, 70, 70, 3, 8, 8, 71, 8, 3, 72, 72, 73, 72, 3, 3, 3, 0 };
    }

    private static byte[] init__json_trans_targs_0() {
        return new byte[] { 35, 1, 3, 0, 4, 36, 36, 36, 36, 1, 6, 5, 13, 17, 22, 37, 7, 8, 9, 7, 8, 9, 7, 10, 20, 21, 11, 11, 11, 12, 17, 19, 37, 11, 12, 19, 14, 16, 15, 14, 12, 18, 17, 11, 9, 5, 24, 23, 27, 31, 34, 25, 38, 25, 25, 26, 31, 33, 38, 25, 26, 33, 28, 30, 29, 28, 26, 32, 31, 25, 23, 2, 36, 2 };
    }

    private static byte[] init__json_trans_actions_0() {
        return new byte[] { 13, 0, 15, 0, 0, 7, 3, 11, 1, 11, 17, 0, 20, 0, 0, 5, 1, 1, 1, 0, 0, 0, 11, 13, 15, 0, 7, 3, 1, 1, 1, 1, 23, 0, 0, 0, 0, 0, 0, 11, 11, 0, 11, 11, 11, 11, 13, 0, 15, 0, 0, 7, 9, 3, 1, 1, 1, 1, 26, 0, 0, 0, 0, 0, 0, 11, 11, 0, 11, 11, 11, 1, 0, 0 };
    }

    private static byte[] init__json_eof_actions_0() {
        return new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 };
    }

    private void addChild(@Null final String name, final JsonValue child) {
        child.setName(name);
        if (this.current == null) {
            this.current = child;
            this.root = child;
        }
        else if (this.current.isArray() || this.current.isObject()) {
            child.parent = this.current;
            if (this.current.size == 0) {
                this.current.child = child;
            }
            else {
                final JsonValue last = this.lastChild.pop();
                last.next = child;
                child.prev = last;
            }
            this.lastChild.add(child);
            final JsonValue current = this.current;
            ++current.size;
        }
        else {
            this.root = this.current;
        }
    }

    protected void startObject(@Null final String name) {
        final JsonValue value = new JsonValue(JsonValue.ValueType.object);
        if (this.current != null) {
            this.addChild(name, value);
        }
        this.elements.add(value);
        this.current = value;
    }

    protected void startArray(@Null final String name) {
        final JsonValue value = new JsonValue(JsonValue.ValueType.array);
        if (this.current != null) {
            this.addChild(name, value);
        }
        this.elements.add(value);
        this.current = value;
    }

    protected void pop() {
        this.root = this.elements.pop();
        if (this.current.size > 0) {
            this.lastChild.pop();
        }
        this.current = ((this.elements.size > 0) ? this.elements.peek() : null);
    }

    protected void string(final String name, final String value) {
        this.addChild(name, new JsonValue(value));
    }

    protected void number(final String name, final double value, final String stringValue) {
        this.addChild(name, new JsonValue(value, stringValue));
    }

    protected void number(final String name, final long value, final String stringValue) {
        this.addChild(name, new JsonValue(value, stringValue));
    }

    protected void bool(final String name, final boolean value) {
        this.addChild(name, new JsonValue(value));
    }

    private String unescape(final String value) {
        final int length = value.length();
        final com.badlogic.gdx.utils.StringBuilder buffer = new com.badlogic.gdx.utils.StringBuilder(length + 16);
        int i = 0;
        while (i < length) {
            char c = value.charAt(i++);
            if (c != '\\') {
                buffer.append(c);
            }
            else {
                if (i == length) {
                    break;
                }
                c = value.charAt(i++);
                if (c == 'u') {
                    buffer.append(Character.toChars(Integer.parseInt(value.substring(i, i + 4), 16)));
                    i += 4;
                }
                else {
                    switch (c) {
                        case '\"':
                        case '/':
                        case '\\': {
                            break;
                        }
                        case 'b': {
                            c = '\b';
                            break;
                        }
                        case 'f': {
                            c = '\f';
                            break;
                        }
                        case 'n': {
                            c = '\n';
                            break;
                        }
                        case 'r': {
                            c = '\r';
                            break;
                        }
                        case 't': {
                            c = '\t';
                            break;
                        }
                        default: {
                            throw new SerializationException("Illegal escaped character: \\" + c);
                        }
                    }
                    buffer.append(c);
                }
            }
        }
        return buffer.toString();
    }

    static {
        _json_actions = init__json_actions_0();
        _json_key_offsets = init__json_key_offsets_0();
        _json_trans_keys = init__json_trans_keys_0();
        _json_single_lengths = init__json_single_lengths_0();
        _json_range_lengths = init__json_range_lengths_0();
        _json_index_offsets = init__json_index_offsets_0();
        _json_indicies = init__json_indicies_0();
        _json_trans_targs = init__json_trans_targs_0();
        _json_trans_actions = init__json_trans_actions_0();
        _json_eof_actions = init__json_eof_actions_0();
    }
}
