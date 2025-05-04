package team.rainfall.fontFix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CS_Frame extends JFrame {
    private JTextField pathField;
    private JButton selectButton;
    private JButton okButton;
    private JButton cancelButton;
    private JProgressBar progressBar;

    public CS_Frame() {
        // 设置窗口标题和布局
        setTitle("CompactScale");
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);

        // 创建顶部面板 - 文件路径选择和选择按钮
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        pathField = new JTextField();
        pathField.setEditable(false);
        pathField.setToolTipText("Scale目录路径");

        selectButton = new JButton("选择目录");
        topPanel.add(new JLabel("Scale目录:"), BorderLayout.WEST);
        topPanel.add(pathField, BorderLayout.CENTER);
        topPanel.add(selectButton, BorderLayout.EAST);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        okButton = new JButton("确定");
        cancelButton = new JButton("取消");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // 创建进度条
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setString("等待操作...");
        progressBar.setVisible(false);

        // 添加组件到窗口
        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);

        // 添加选择目录按钮事件监听器
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("选择Scale目录");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int returnValue = fileChooser.showOpenDialog(CS_Frame.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    pathField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        // 添加确定按钮事件监听器
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPath = pathField.getText();
                if (!selectedPath.isEmpty()) {
                    startProgress("处理中: " + selectedPath);
                } else {
                    JOptionPane.showMessageDialog(CS_Frame.this,
                            "请先选择一个目录", "警告", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // 添加取消按钮事件监听器
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void startProgress(String message) {
        progressBar.setVisible(true);
        progressBar.setString(message);
        progressBar.setValue(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(pathField.getText());
                File file2 = new File(file,"compactScale");
                try {
                    CS_Creator.createCompactFile(file2,file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CS_Frame frame = new CS_Frame();
                frame.setLocationRelativeTo(null); // 居中显示
                frame.setVisible(true);
            }
        });
    }
}