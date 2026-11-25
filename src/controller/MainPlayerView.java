package view;

import controller.PlayerController;
import model.Player;
import model.Injury;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainPlayerView extends JFrame {
    private PlayerController controller;
    private Player currentSelectedPlayer;

    // Player table components
    private JTable playerTable;
    private DefaultTableModel playerTableModel;

    // Injury list components
    private JList<String> injuryList;
    private DefaultListModel<String> injuryListModel;
    private JTextArea injuryDetailArea;

    // Add/Edit Player dialog components
    private JButton addPlayerButton;
    private JButton editPlayerButton;
    private JButton deletePlayerButton;

    // Status control components
    private JComboBox<String> statusCombo;
    private JButton updateStatusButton;

    // Injury management components
    private JTextField injuryTypeField;
    private JTextField injuryDateField;
    private JComboBox<String> severityCombo;
    private JTextField bodyPartField;
    private JCheckBox surgeryCheckBox;
    private JComboBox<String> recoveryStatusCombo;
    private JTextField weeksRecoveryField;
    private JButton addInjuryButton;
    private JButton deleteInjuryButton;

    private JLabel playerDetailLabel;
    private JLabel statusLabel;

    private final Color GRASS_GREEN = new Color(46, 125, 50);
    private final Color LIGHT_GREEN = new Color(76, 175, 80);
    private final Color WHITE = Color.WHITE;
    private final Color BLACK = Color.BLACK;
    private final Color RED = new Color(220, 20, 60);
    private final Color YELLOW = new Color(255, 215, 0);
    private final Color DARK_GREEN = new Color(34, 139, 34);
    private final Color SOIL_BROWN = new Color(121, 85, 72);

    public MainPlayerView(PlayerController controller) {
        this.controller = controller;
        initializeUI();
        loadPlayerData();
    }

    private void initializeUI() {
        setTitle("⚽ Soccer Injury Tracker - Complete Player Management ⚽");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(GRASS_GREEN);

        // Title
        JLabel titleLabel = new JLabel("⚽ Player Injury Management System ⚽", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Split pane: Left side (Player table), Right side (Injury management)
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.5);
        splitPane.setBorder(BorderFactory.createLineBorder(WHITE, 2));

        splitPane.setLeftComponent(createPlayerPanel());
        splitPane.setRightComponent(createInjuryPanel());

        mainPanel.add(splitPane, BorderLayout.CENTER);
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);

        add(mainPanel);

        setSize(1300, 750);
        setLocationRelativeTo(null);
    }

    private JPanel createPlayerPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(GRASS_GREEN);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(WHITE, 2),
                "🏃 Player Roster",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                WHITE));

        // Player table
        String[] columns = {"ID", "Name", "Age", "Position", "Team", "Jersey", "Status"};
        playerTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        playerTable = new JTable(playerTableModel);
        playerTable.setRowHeight(30);
        playerTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        playerTable.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // Color renderer for status column
        playerTable.getColumnModel().getColumn(6).setCellRenderer(new StatusCellRenderer());

        playerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = playerTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String playerId = (String) playerTableModel.getValueAt(selectedRow, 0);
                    currentSelectedPlayer = controller.getPlayerById(playerId);
                    updateInjuryPanel();
                    playerDetailLabel.setText("Selected: " + currentSelectedPlayer.getName() +
                            " (" + currentSelectedPlayer.getPlayerID() + ")");
                    statusCombo.setSelectedItem(currentSelectedPlayer.getClearanceStatus());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(playerTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(WHITE, 1));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Button panel for player CRUD
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(GRASS_GREEN);

        addPlayerButton = createButton("➕ Add Player");
        editPlayerButton = createButton("✏️ Edit Player");
        deletePlayerButton = createButton("❌ Delete Player");

        addPlayerButton.addActionListener(e -> showAddPlayerDialog());
        editPlayerButton.addActionListener(e -> showEditPlayerDialog());
        deletePlayerButton.addActionListener(e -> deleteSelectedPlayer());

        buttonPanel.add(addPlayerButton);
        buttonPanel.add(editPlayerButton);
        buttonPanel.add(deletePlayerButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Player detail label
        playerDetailLabel = new JLabel("Select a player to view injuries", SwingConstants.CENTER);
        playerDetailLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        playerDetailLabel.setForeground(WHITE);
        playerDetailLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(playerDetailLabel, BorderLayout.NORTH);

        return panel;
    }

    private void showAddPlayerDialog() {
        JDialog dialog = new JDialog(this, "➕ Add New Player", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(450, 450);
        dialog.setLocationRelativeTo(this);

        JPanel formPanel = createPlayerFormPanel(null, false);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("➕ Add");
        JButton cancelButton = new JButton("❌ Cancel");

        saveButton.addActionListener(e -> {
            // Get values from form
            Component[] components = formPanel.getComponents();
            String name = ((JTextField)((JPanel)components[0]).getComponent(1)).getText();
            int age = Integer.parseInt(((JTextField)((JPanel)components[1]).getComponent(1)).getText());
            String position = ((JTextField)((JPanel)components[2]).getComponent(1)).getText();
            String team = ((JTextField)((JPanel)components[3]).getComponent(1)).getText();
            String playerId = ((JTextField)((JPanel)components[4]).getComponent(1)).getText();
            int jersey = Integer.parseInt(((JTextField)((JPanel)components[5]).getComponent(1)).getText());
            String foot = ((JTextField)((JPanel)components[6]).getComponent(1)).getText();

            boolean success = controller.addPlayer(name, age, playerId, position, team, jersey, foot);
            if (success) {
                JOptionPane.showMessageDialog(dialog, "✅ Player added successfully!");
                dialog.dispose();
                refreshPlayerTable();
            } else {
                JOptionPane.showMessageDialog(dialog, "❌ Player ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void showEditPlayerDialog() {
        if (currentSelectedPlayer == null) {
            JOptionPane.showMessageDialog(this, "Please select a player first!");
            return;
        }

        JDialog dialog = new JDialog(this, "✏️ Edit Player: " + currentSelectedPlayer.getName(), true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(450, 450);
        dialog.setLocationRelativeTo(this);

        JPanel formPanel = createPlayerFormPanel(currentSelectedPlayer, true);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("✏️ Update");
        JButton cancelButton = new JButton("❌ Cancel");

        saveButton.addActionListener(e -> {
            Component[] components = formPanel.getComponents();
            String name = ((JTextField)((JPanel)components[0]).getComponent(1)).getText();
            int age = Integer.parseInt(((JTextField)((JPanel)components[1]).getComponent(1)).getText());
            String position = ((JTextField)((JPanel)components[2]).getComponent(1)).getText();
            String team = ((JTextField)((JPanel)components[3]).getComponent(1)).getText();
            String newId = ((JTextField)((JPanel)components[4]).getComponent(1)).getText();
            int jersey = Integer.parseInt(((JTextField)((JPanel)components[5]).getComponent(1)).getText());
            String foot = ((JTextField)((JPanel)components[6]).getComponent(1)).getText();

            boolean success = controller.updatePlayer(
                    currentSelectedPlayer.getPlayerID(), name, age, newId,
                    position, team, jersey, foot
            );

            if (success) {
                JOptionPane.showMessageDialog(dialog, "✅ Player updated successfully!");
                dialog.dispose();
                refreshPlayerTable();
                currentSelectedPlayer = controller.getPlayerById(newId);
                playerDetailLabel.setText("Selected: " + currentSelectedPlayer.getName() +
                        " (" + currentSelectedPlayer.getPlayerID() + ")");
            } else {
                JOptionPane.showMessageDialog(dialog, "❌ Player ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private JPanel createPlayerFormPanel(Player player, boolean isEdit) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(GRASS_GREEN);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;

        String[] labels = {"👤 Name:", "🎂 Age:", "⚙️ Position:", "🏟️ Team:",
                "🆔 Player ID:", "🔢 Jersey #:", "👟 Preferred Foot:"};
        JTextField[] fields = new JTextField[7];

        for (int i = 0; i < labels.length; i++) {
            JPanel rowPanel = new JPanel(new GridLayout(1, 2, 10, 0));
            rowPanel.setBackground(GRASS_GREEN);

            JLabel label = new JLabel(labels[i]);
            label.setForeground(WHITE);
            label.setFont(new Font("SansSerif", Font.BOLD, 12));

            fields[i] = new JTextField(15);
            fields[i].setFont(new Font("SansSerif", Font.PLAIN, 12));

            rowPanel.add(label);
            rowPanel.add(fields[i]);

            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(rowPanel, gbc);
        }

        if (player != null) {
            fields[0].setText(player.getName());
            fields[1].setText(String.valueOf(player.getAge()));
            fields[2].setText(player.getPosition());
            fields[3].setText(player.getTeam());
            fields[4].setText(player.getPlayerID());
            fields[5].setText(String.valueOf(player.getJerseyNumber()));
            fields[6].setText(player.getPreferredFoot());
        }

        return panel;
    }

    private void deleteSelectedPlayer() {
        if (currentSelectedPlayer == null) {
            JOptionPane.showMessageDialog(this, "Please select a player first!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "❓ Delete player: " + currentSelectedPlayer.getName() + "?\nThis will also delete all their injuries!",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            controller.deletePlayer(currentSelectedPlayer.getPlayerID());
            currentSelectedPlayer = null;
            refreshPlayerTable();
            injuryListModel.clear();
            injuryDetailArea.setText("");
            playerDetailLabel.setText("Select a player to view injuries");
            JOptionPane.showMessageDialog(this, "✅ Player deleted successfully!");
        }
    }

    private JPanel createInjuryPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(GRASS_GREEN);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(WHITE, 2),
                "🏥 Injury Management",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                WHITE));

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 12));

        tabbedPane.addTab("📋 View Injuries", createViewInjuryPanel());
        tabbedPane.addTab("➕ Add Injury", createAddInjuryPanel());
        tabbedPane.addTab("⚡ Update Status", createUpdateStatusPanel());

        panel.add(tabbedPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createViewInjuryPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(GRASS_GREEN);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Injury list
        injuryListModel = new DefaultListModel<>();
        injuryList = new JList<>(injuryListModel);
        injuryList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        injuryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        injuryList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showInjuryDetail();
            }
        });

        JScrollPane listScroll = new JScrollPane(injuryList);
        listScroll.setBorder(BorderFactory.createTitledBorder("Player Injuries"));
        panel.add(listScroll, BorderLayout.CENTER);

        // Injury detail area
        injuryDetailArea = new JTextArea(8, 30);
        injuryDetailArea.setEditable(false);
        injuryDetailArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane detailScroll = new JScrollPane(injuryDetailArea);
        detailScroll.setBorder(BorderFactory.createTitledBorder("Injury Details"));
        panel.add(detailScroll, BorderLayout.SOUTH);

        // Delete injury button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(GRASS_GREEN);

        deleteInjuryButton = new JButton("❌ Delete Selected Injury");
        styleButton(deleteInjuryButton);
        deleteInjuryButton.addActionListener(e -> deleteSelectedInjury());
        buttonPanel.add(deleteInjuryButton);

        panel.add(buttonPanel, BorderLayout.NORTH);

        return panel;
    }

    private JPanel createAddInjuryPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(GRASS_GREEN);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Injury Type
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel typeLabel = new JLabel("🏥 Injury Type:");
        typeLabel.setForeground(WHITE);
        panel.add(typeLabel, gbc);
        gbc.gridx = 1;
        injuryTypeField = new JTextField(15);
        panel.add(injuryTypeField, gbc);

        // Injury Date
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel dateLabel = new JLabel("📅 Injury Date (YYYY-MM-DD):");
        dateLabel.setForeground(WHITE);
        panel.add(dateLabel, gbc);
        gbc.gridx = 1;
        injuryDateField = new JTextField(15);
        panel.add(injuryDateField, gbc);

        // Severity
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel severityLabel = new JLabel("⚡ Severity:");
        severityLabel.setForeground(WHITE);
        panel.add(severityLabel, gbc);
        gbc.gridx = 1;
        severityCombo = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        panel.add(severityCombo, gbc);

        // Body Part
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel bodyLabel = new JLabel("📍 Body Part:");
        bodyLabel.setForeground(WHITE);
        panel.add(bodyLabel, gbc);
        gbc.gridx = 1;
        bodyPartField = new JTextField(15);
        panel.add(bodyPartField, gbc);

        // Requires Surgery
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel surgeryLabel = new JLabel("🔪 Requires Surgery:");
        surgeryLabel.setForeground(WHITE);
        panel.add(surgeryLabel, gbc);
        gbc.gridx = 1;
        surgeryCheckBox = new JCheckBox();
        surgeryCheckBox.setBackground(GRASS_GREEN);
        panel.add(surgeryCheckBox, gbc);

        // Weeks Recovery
        gbc.gridx = 0; gbc.gridy = 5;
        JLabel weeksLabel = new JLabel("⏱️ Est. Weeks Recovery:");
        weeksLabel.setForeground(WHITE);
        panel.add(weeksLabel, gbc);
        gbc.gridx = 1;
        weeksRecoveryField = new JTextField(15);
        panel.add(weeksRecoveryField, gbc);

        // Recovery Status
        gbc.gridx = 0; gbc.gridy = 6;
        JLabel recoveryLabel = new JLabel("🔄 Recovery Status:");
        recoveryLabel.setForeground(WHITE);
        panel.add(recoveryLabel, gbc);
        gbc.gridx = 1;
        recoveryStatusCombo = new JComboBox<>(new String[]{"Recovering", "Almost Recovered", "Recovered"});
        panel.add(recoveryStatusCombo, gbc);

        // Add button
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        addInjuryButton = new JButton("➕ Add Injury to Selected Player");
        styleButton(addInjuryButton);
        addInjuryButton.addActionListener(e -> addInjuryToPlayer());
        panel.add(addInjuryButton, gbc);

        return panel;
    }

    private JPanel createUpdateStatusPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(GRASS_GREEN);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel instructionLabel = new JLabel("Update Player Clearance Status:");
        instructionLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        instructionLabel.setForeground(WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(instructionLabel, gbc);

        JLabel statusTextLabel = new JLabel("Select Status:");
        statusTextLabel.setForeground(WHITE);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(statusTextLabel, gbc);

        statusCombo = new JComboBox<>(new String[]{"GREEN", "YELLOW", "RED"});
        gbc.gridx = 1;
        panel.add(statusCombo, gbc);

        updateStatusButton = new JButton("✏️ Update Clearance Status");
        styleButton(updateStatusButton);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(updateStatusButton, gbc);

        updateStatusButton.addActionListener(e -> updatePlayerClearanceStatus());

        // Legend panel
        JPanel legendPanel = new JPanel(new FlowLayout());
        legendPanel.setBackground(GRASS_GREEN);
        legendPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(WHITE, 1),
                "Status Legend",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                WHITE));

        JLabel redLabel = new JLabel("🔴 RED: Not Cleared - Active injury");
        redLabel.setForeground(RED);
        JLabel yellowLabel = new JLabel("🟡 YELLOW: Almost Cleared - Recovering");
        yellowLabel.setForeground(YELLOW);
        JLabel greenLabel = new JLabel("🟢 GREEN: Cleared - Ready to play");
        greenLabel.setForeground(DARK_GREEN);

        legendPanel.add(redLabel);
        legendPanel.add(yellowLabel);
        legendPanel.add(greenLabel);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(legendPanel, gbc);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(SOIL_BROWN);

        statusLabel = new JLabel(" Ready ", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        statusLabel.setForeground(WHITE);
        panel.add(statusLabel, BorderLayout.CENTER);

        JButton exitButton = new JButton("🚪 Exit");
        styleButton(exitButton);
        exitButton.addActionListener(e -> controller.exitApplication());
        panel.add(exitButton, BorderLayout.EAST);

        return panel;
    }

    private void styleButton(JButton button) {
        button.setBackground(LIGHT_GREEN);
        button.setForeground(BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 11));
        button.setBorder(BorderFactory.createLineBorder(WHITE, 1));
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        styleButton(button);
        return button;
    }

    public void loadPlayerData() {
        playerTableModel.setRowCount(0);
        List<Player> players = controller.getAllPlayers();

        for (Player p : players) {
            Object[] row = {
                    p.getPlayerID(),
                    p.getName(),
                    p.getAge(),
                    p.getPosition(),
                    p.getTeam(),
                    p.getJerseyNumber(),
                    p.getClearanceStatus()
            };
            playerTableModel.addRow(row);
        }

        statusLabel.setText(" Total Players: " + players.size() + " ");
    }

    private void updateInjuryPanel() {
        injuryListModel.clear();
        injuryDetailArea.setText("");

        if (currentSelectedPlayer != null) {
            List<Injury> injuries = controller.getPlayerInjuries(currentSelectedPlayer.getPlayerID());
            for (int i = 0; i < injuries.size(); i++) {
                injuryListModel.addElement((i + 1) + ". " + injuries.get(i).toShortString());
            }

            if (injuries.isEmpty()) {
                injuryListModel.addElement("No injuries recorded for this player");
            }
        }
    }

    private void showInjuryDetail() {
        int selectedIndex = injuryList.getSelectedIndex();
        if (currentSelectedPlayer != null && selectedIndex >= 0) {
            List<Injury> injuries = controller.getPlayerInjuries(currentSelectedPlayer.getPlayerID());
            if (selectedIndex < injuries.size()) {
                Injury injury = injuries.get(selectedIndex);
                injuryDetailArea.setText(injury.toString());
            }
        }
    }

    private void addInjuryToPlayer() {
        if (currentSelectedPlayer == null) {
            JOptionPane.showMessageDialog(this, "Please select a player first!");
            return;
        }

        String injuryType = injuryTypeField.getText().trim();
        String injuryDate = injuryDateField.getText().trim();
        String severity = (String) severityCombo.getSelectedItem();
        String bodyPart = bodyPartField.getText().trim();
        boolean requiresSurgery = surgeryCheckBox.isSelected();
        String recoveryStatus = (String) recoveryStatusCombo.getSelectedItem();
        int weeksRecovery = 0;

        try {
            String weeksStr = weeksRecoveryField.getText().trim();
            if (!weeksStr.isEmpty()) {
                weeksRecovery = Integer.parseInt(weeksStr);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Weeks Recovery must be a number");
            return;
        }

        String result = controller.addInjury(
                currentSelectedPlayer.getPlayerID(), injuryType, injuryDate,
                severity, bodyPart, requiresSurgery, weeksRecovery, recoveryStatus
        );

        JOptionPane.showMessageDialog(this, result);

        // Clear fields
        injuryTypeField.setText("");
        injuryDateField.setText("");
        bodyPartField.setText("");
        surgeryCheckBox.setSelected(false);
        weeksRecoveryField.setText("");

        updateInjuryPanel();
        refreshPlayerTable();
    }

    private void deleteSelectedInjury() {
        if (currentSelectedPlayer == null) {
            JOptionPane.showMessageDialog(this, "Please select a player first!");
            return;
        }

        int selectedIndex = injuryList.getSelectedIndex();
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select an injury to delete!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Delete this injury?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String result = controller.deleteInjury(currentSelectedPlayer.getPlayerID(), selectedIndex);
            JOptionPane.showMessageDialog(this, result);
            updateInjuryPanel();
            refreshPlayerTable();
        }
    }

    private void updatePlayerClearanceStatus() {
        if (currentSelectedPlayer == null) {
            JOptionPane.showMessageDialog(this, "Please select a player first!");
            return;
        }

        String newStatus = (String) statusCombo.getSelectedItem();
        controller.updateClearanceStatus(currentSelectedPlayer.getPlayerID(), newStatus);
        currentSelectedPlayer = controller.getPlayerById(currentSelectedPlayer.getPlayerID());
        refreshPlayerTable();
        playerDetailLabel.setText("Selected: " + currentSelectedPlayer.getName() +
                " (" + currentSelectedPlayer.getPlayerID() + ") [Status: " + newStatus + "]");
    }

    public void refreshPlayerTable() {
        loadPlayerData();
        if (currentSelectedPlayer != null) {
            currentSelectedPlayer = controller.getPlayerById(currentSelectedPlayer.getPlayerID());
            updateInjuryPanel();
        }
    }

    // Custom cell renderer for status column
    private class StatusCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String status = (String) value;
            if (status != null) {
                switch (status) {
                    case "RED":
                        c.setBackground(RED);
                        c.setForeground(Color.WHITE);
                        setText("🔴 NOT CLEARED");
                        break;
                    case "YELLOW":
                        c.setBackground(YELLOW);
                        c.setForeground(Color.BLACK);
                        setText("🟡 ALMOST CLEAR");
                        break;
                    case "GREEN":
                        c.setBackground(DARK_GREEN);
                        c.setForeground(Color.WHITE);
                        setText("🟢 CLEARED");
                        break;
                    default:
                        c.setBackground(Color.GRAY);
                        c.setForeground(Color.WHITE);
                        setText(status);
                }
            }
            setHorizontalAlignment(CENTER);
            return c;
        }
    }
}