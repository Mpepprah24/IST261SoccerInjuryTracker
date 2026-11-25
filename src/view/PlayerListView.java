package view;

import controller.PlayerController;
import model.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PlayerListView extends JFrame {
    private PlayerController controller;
    private JTable playerTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton;
    private JButton closeButton;
    private JLabel statusLabel;

    private final Color GRASS_GREEN = new Color(46, 125, 50);
    private final Color LIGHT_GREEN = new Color(76, 175, 80);
    private final Color WHITE = Color.WHITE;
    private final Color BLACK = Color.BLACK;
    private final Color RED = new Color(220, 20, 60);
    private final Color YELLOW = new Color(255, 215, 0);
    private final Color DARK_GREEN = new Color(34, 139, 34);

    public PlayerListView(PlayerController controller) {
        this.controller = controller;
        initializeUI();
        loadPlayerData();
    }

    private void initializeUI() {
        setTitle("⚽ Soccer Injury Tracker - Player List ⚽");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(GRASS_GREEN);

        JLabel titleLabel = new JLabel("⚽ Player Roster ⚽", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        setupTable();
        JScrollPane scrollPane = new JScrollPane(playerTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(WHITE, 2));
        scrollPane.getViewport().setBackground(WHITE);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("⚽ Ready ⚽");
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        statusLabel.setForeground(WHITE);
        statusLabel.setBackground(new Color(121, 85, 72));
        statusLabel.setOpaque(true);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(statusLabel, BorderLayout.SOUTH);

        setSize(900, 550);
        setLocationRelativeTo(null);
    }

    private void setupTable() {
        String[] columns = {"ID", "Name", "Age", "Position", "Team", "Jersey #", "Foot", "Status"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        playerTable = new JTable(tableModel);
        playerTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
        playerTable.setRowHeight(28);
        playerTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        playerTable.getTableHeader().setBackground(LIGHT_GREEN);
        playerTable.getTableHeader().setForeground(BLACK);
        playerTable.setSelectionBackground(LIGHT_GREEN);
        playerTable.setSelectionForeground(BLACK);

        // Set column widths
        playerTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        playerTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        playerTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        playerTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        playerTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        playerTable.getColumnModel().getColumn(5).setPreferredWidth(70);
        playerTable.getColumnModel().getColumn(6).setPreferredWidth(80);
        playerTable.getColumnModel().getColumn(7).setPreferredWidth(100);

        // Color renderer for status column
        playerTable.getColumnModel().getColumn(7).setCellRenderer(new StatusCellRenderer());
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(GRASS_GREEN);

        refreshButton = createButton("🔄 Refresh");
        closeButton = createButton("❌ Close");

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
        if (command.contains("Refresh")) {
            loadPlayerData();
        } else if (command.contains("Close")) {
            dispose();
        }
    }

    public void loadPlayerData() {
        tableModel.setRowCount(0);

        List<Player> players = controller.getAllPlayers();
        for (Player p : players) {
            String statusDisplay = "";
            switch (p.getClearanceStatus()) {
                case "RED":
                    statusDisplay = "🔴 NOT CLEARED";
                    break;
                case "YELLOW":
                    statusDisplay = "🟡 ALMOST CLEAR";
                    break;
                case "GREEN":
                    statusDisplay = "🟢 CLEARED";
                    break;
                default:
                    statusDisplay = p.getClearanceStatus();
            }

            Object[] row = {
                    p.getPlayerID(),
                    p.getName(),
                    p.getAge(),
                    p.getPosition(),
                    p.getTeam(),
                    p.getJerseyNumber(),
                    p.getPreferredFoot(),
                    statusDisplay
            };
            tableModel.addRow(row);
        }

        statusLabel.setText("⚽ Total Players: " + players.size() + " ⚽");
    }

    // Custom cell renderer for status column
    private class StatusCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String status = (String) value;
            if (status != null) {
                if (status.contains("NOT CLEARED")) {
                    c.setBackground(RED);
                    c.setForeground(Color.WHITE);
                } else if (status.contains("ALMOST")) {
                    c.setBackground(YELLOW);
                    c.setForeground(Color.BLACK);
                } else if (status.contains("CLEARED")) {
                    c.setBackground(DARK_GREEN);
                    c.setForeground(Color.WHITE);
                }
            }
            setHorizontalAlignment(CENTER);
            return c;
        }
    }
}