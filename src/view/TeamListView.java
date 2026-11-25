package view;

import controller.PlayerController;
import model.Team;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TeamListView extends JFrame {
    private PlayerController controller;
    private JTable teamTable;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton closeButton;
    private JLabel statusLabel;

    private final Color GRASS_GREEN = new Color(46, 125, 50);
    private final Color LIGHT_GREEN = new Color(76, 175, 80);
    private final Color WHITE = Color.WHITE;
    private final Color BLACK = Color.BLACK;

    public TeamListView(PlayerController controller) {
        this.controller = controller;
        initializeUI();
        refreshTeamList();
    }

    private void initializeUI() {
        setTitle("🏆 Soccer Injury Tracker - Team List 🏆");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(GRASS_GREEN);

        JLabel titleLabel = new JLabel("🏆 Team Roster 🏆", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        setupTable();
        JScrollPane scrollPane = new JScrollPane(teamTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(WHITE, 2));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("🏆 Ready 🏆");
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        statusLabel.setForeground(WHITE);
        statusLabel.setBackground(new Color(121, 85, 72));
        statusLabel.setOpaque(true);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(statusLabel, BorderLayout.SOUTH);

        setSize(800, 500);
        setLocationRelativeTo(null);
    }

    private void setupTable() {
        String[] columns = {"Team Name", "Coach", "Location", "League", "Roster"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        teamTable = new JTable(tableModel);
        teamTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
        teamTable.setRowHeight(25);
        teamTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        teamTable.getTableHeader().setBackground(LIGHT_GREEN);
        teamTable.getTableHeader().setForeground(BLACK);
        teamTable.setSelectionBackground(LIGHT_GREEN);
        teamTable.setSelectionForeground(BLACK);

        teamTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editSelectedTeam();
                }
            }
        });
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(GRASS_GREEN);

        addButton = createButton("➕ Add Team");
        editButton = createButton("✏️ Edit Selected");
        deleteButton = createButton("❌ Delete Selected");
        refreshButton = createButton("🔄 Refresh");
        closeButton = createButton("❌ Close");

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(refreshButton);
        panel.add(closeButton);

        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBackground(LIGHT_GREEN);
        button.setForeground(BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(WHITE, 2));
        button.addActionListener(e -> handleButtonAction(e.getActionCommand()));
        return button;
    }

    private void handleButtonAction(String command) {
        if (command.contains("Add")) {
            showTeamDialog(null, false);
        } else if (command.contains("Edit")) {
            editSelectedTeam();
        } else if (command.contains("Delete")) {
            deleteSelectedTeam();
        } else if (command.contains("Refresh")) {
            refreshTeamList();
        } else if (command.contains("Close")) {
            dispose();
        }
    }

    private void editSelectedTeam() {
        int selectedRow = teamTable.getSelectedRow();
        if (selectedRow >= 0) {
            String teamName = (String) tableModel.getValueAt(selectedRow, 0);
            List<Team> teams = controller.getAllTeams();
            Team team = null;
            for (Team t : teams) {
                if (t.getTeamName().equals(teamName)) {
                    team = t;
                    break;
                }
            }
            if (team != null) {
                showTeamDialog(team, true);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "❌ Please select a team to edit",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteSelectedTeam() {
        int selectedRow = teamTable.getSelectedRow();
        if (selectedRow >= 0) {
            String teamName = (String) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "❓ Delete " + teamName + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteTeam(teamName);
                refreshTeamList();
                JOptionPane.showMessageDialog(this,
                        "✅ Team deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "❌ Please select a team to delete",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showTeamDialog(Team team, boolean isEditMode) {
        JDialog dialog = new JDialog(this, isEditMode ? "✏️ Edit Team" : "➕ Add Team", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(450, 350);
        dialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(GRASS_GREEN);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("SansSerif", Font.BOLD, 12);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 12);

        JTextField nameField = new JTextField(20);
        JTextField coachField = new JTextField(20);
        JTextField locationField = new JTextField(20);
        JTextField leagueField = new JTextField(20);
        JTextField rosterField = new JTextField(20);

        if (team != null) {
            nameField.setText(team.getTeamName());
            coachField.setText(team.getCoach());
            locationField.setText(team.getLocation());
            leagueField.setText(team.getLeague());
            rosterField.setText(team.getRoster());
        }

        String[] labels = {"🏟️ Team Name:", "👔 Coach:", "📍 Location:", "🏆 League:", "📋 Roster:"};
        JTextField[] fields = {nameField, coachField, locationField, leagueField, rosterField};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            JLabel label = new JLabel(labels[i]);
            label.setFont(labelFont);
            label.setForeground(WHITE);
            formPanel.add(label, gbc);

            gbc.gridx = 1; gbc.gridy = i;
            fields[i].setFont(fieldFont);
            formPanel.add(fields[i], gbc);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(GRASS_GREEN);

        JButton saveButton = new JButton(isEditMode ? "✏️ Update" : "➕ Add");
        JButton cancelButton = new JButton("❌ Cancel");

        saveButton.addActionListener(e -> {
            if (isEditMode && team != null) {
                controller.updateTeam(team.getTeamName(), nameField.getText(),
                        coachField.getText(), locationField.getText(),
                        leagueField.getText(), rosterField.getText());
            } else {
                controller.addTeam(nameField.getText(), coachField.getText(),
                        locationField.getText(), leagueField.getText(),
                        rosterField.getText());
            }
            dialog.dispose();
            refreshTeamList();
            JOptionPane.showMessageDialog(this,
                    isEditMode ? "✅ Team updated successfully!" : "✅ Team added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public void refreshTeamList() {
        tableModel.setRowCount(0);
        List<Team> teams = controller.getAllTeams();
        for (Team t : teams) {
            Object[] row = {t.getTeamName(), t.getCoach(), t.getLocation(),
                    t.getLeague(), t.getRoster()};
            tableModel.addRow(row);
        }

        statusLabel.setText("🏆 Total Teams: " + teams.size() + " 🏆");

        boolean hasData = !teams.isEmpty();
        editButton.setEnabled(hasData);
        deleteButton.setEnabled(hasData);
    }
}