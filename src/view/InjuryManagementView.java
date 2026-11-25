package view;

import controller.InjuryController;

import javax.swing.*;
import java.awt.*;

public class InjuryManagementView extends JFrame {
    private InjuryController controller;

    private JTextField viewPlayerIdField;
    private JTextArea viewResultArea;

    public InjuryManagementView(InjuryController controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Soccer Injury Tracker - Injury Management (HashMap)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Injury Management System (HashMap Collection)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("View Injuries", createViewPanel());
        tabbedPane.addTab("Statistics", createStatsPanel());

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        setSize(600, 500);
        setLocationRelativeTo(null);
    }

    private JPanel createViewPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new FlowLayout());

        JLabel playerLabel = new JLabel("Player ID:");
        inputPanel.add(playerLabel);

        viewPlayerIdField = new JTextField(15);
        inputPanel.add(viewPlayerIdField);

        JButton viewButton = new JButton("View Injuries");
        inputPanel.add(viewButton);

        viewButton.addActionListener(e -> performView());

        viewResultArea = new JTextArea(15, 40);
        viewResultArea.setEditable(false);
        viewResultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(viewResultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Injury List"));

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea statsArea = new JTextArea(10, 40);
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        statsArea.setText(controller.getStatistics());

        JScrollPane scrollPane = new JScrollPane(statsArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("HashMap Statistics"));

        JButton refreshButton = new JButton("Refresh Statistics");
        refreshButton.addActionListener(e -> statsArea.setText(controller.getStatistics()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void performView() {
        String playerId = viewPlayerIdField.getText().trim();

        if (playerId.isEmpty()) {
            viewResultArea.setText("Please enter a Player ID to view injuries");
            return;
        }

        String result = controller.getPlayerInjuries(playerId);
        viewResultArea.setText(result);
    }
}