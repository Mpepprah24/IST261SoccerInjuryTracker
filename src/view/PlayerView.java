package view;

import controller.PlayerController;
import model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends JDialog {
    private PlayerController controller;
    private boolean isEditMode;
    private String originalPlayerId;

    private JTextField nameField;
    private JTextField ageField;
    private JTextField positionField;
    private JTextField teamField;
    private JTextField playerIdField;
    private JTextField jerseyField;
    private JTextField footField;

    private JButton saveButton;
    private JButton cancelButton;

    private final Color GRASS_GREEN = new Color(46, 125, 50);
    private final Color LIGHT_GREEN = new Color(76, 175, 80);
    private final Color WHITE = Color.WHITE;
    private final Color BLACK = Color.BLACK;

    public PlayerView(PlayerController controller, JFrame parent, boolean isEditMode) {
        super(parent, isEditMode ? "✏️ Edit Player" : "➕ Add Player", true);
        this.controller = controller;
        this.isEditMode = isEditMode;
        initializeUI();
        setLocationRelativeTo(parent);
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(GRASS_GREEN);

        mainPanel.add(createFormPanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        setSize(450, 400);
        setResizable(false);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(GRASS_GREEN);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("SansSerif", Font.BOLD, 12);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 12);

        // Row 0: Name
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel nameLabel = new JLabel("👤 Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(WHITE);
        panel.add(nameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1.0;
        nameField = new JTextField(20);
        nameField.setFont(fieldFont);
        panel.add(nameField, gbc);

        // Row 1: Age
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0;
        JLabel ageLabel = new JLabel("🎂 Age:");
        ageLabel.setFont(labelFont);
        ageLabel.setForeground(WHITE);
        panel.add(ageLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1.0;
        ageField = new JTextField(20);
        ageField.setFont(fieldFont);
        panel.add(ageField, gbc);

        // Row 2: Position
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel positionLabel = new JLabel("⚙️ Position:");
        positionLabel.setFont(labelFont);
        positionLabel.setForeground(WHITE);
        panel.add(positionLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        positionField = new JTextField(20);
        positionField.setFont(fieldFont);
        panel.add(positionField, gbc);

        // Row 3: Team
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel teamLabel = new JLabel("🏟️ Team:");
        teamLabel.setFont(labelFont);
        teamLabel.setForeground(WHITE);
        panel.add(teamLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        teamField = new JTextField(20);
        teamField.setFont(fieldFont);
        panel.add(teamField, gbc);

        // Row 4: Player ID
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel idLabel = new JLabel("🆔 Player ID:");
        idLabel.setFont(labelFont);
        idLabel.setForeground(WHITE);
        panel.add(idLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        playerIdField = new JTextField(20);
        playerIdField.setFont(fieldFont);
        panel.add(playerIdField, gbc);

        // Row 5: Jersey Number
        gbc.gridx = 0; gbc.gridy = 5;
        JLabel jerseyLabel = new JLabel("🔢 Jersey #:");
        jerseyLabel.setFont(labelFont);
        jerseyLabel.setForeground(WHITE);
        panel.add(jerseyLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        jerseyField = new JTextField(20);
        jerseyField.setFont(fieldFont);
        panel.add(jerseyField, gbc);

        // Row 6: Preferred Foot
        gbc.gridx = 0; gbc.gridy = 6;
        JLabel footLabel = new JLabel("👟 Preferred Foot:");
        footLabel.setFont(labelFont);
        footLabel.setForeground(WHITE);
        panel.add(footLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 6;
        footField = new JTextField(20);
        footField.setFont(fieldFont);
        panel.add(footField, gbc);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(GRASS_GREEN);

        saveButton = new JButton(isEditMode ? "✏️ Update" : "➕ Add");
        saveButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        saveButton.setBackground(LIGHT_GREEN);
        saveButton.setForeground(BLACK);
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createLineBorder(WHITE, 2));
        saveButton.addActionListener(e -> {
            // For now, just show message (can be implemented fully later)
            JOptionPane.showMessageDialog(this, "Player saved (simplified mode)");
            dispose();
        });

        cancelButton = new JButton("❌ Cancel");
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        cancelButton.setBackground(LIGHT_GREEN);
        cancelButton.setForeground(BLACK);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createLineBorder(WHITE, 2));
        cancelButton.addActionListener(e -> dispose());

        panel.add(saveButton);
        panel.add(cancelButton);

        return panel;
    }

    public void setPlayerData(Player player) {
        if (player != null) {
            originalPlayerId = player.getPlayerID();
            nameField.setText(player.getName());
            ageField.setText(String.valueOf(player.getAge()));
            positionField.setText(player.getPosition());
            teamField.setText(player.getTeam());
            playerIdField.setText(player.getPlayerID());
            jerseyField.setText(String.valueOf(player.getJerseyNumber()));
            footField.setText(player.getPreferredFoot());
        }
    }

    public void clearFields() {
        nameField.setText("");
        ageField.setText("");
        positionField.setText("");
        teamField.setText("");
        playerIdField.setText("");
        jerseyField.setText("");
        footField.setText("");
        originalPlayerId = null;
    }

    public void closeDialog() {
        dispose();
    }
}