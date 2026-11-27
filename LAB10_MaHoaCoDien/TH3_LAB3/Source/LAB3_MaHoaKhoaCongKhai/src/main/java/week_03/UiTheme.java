package week_03;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Hệ thống giao diện thống nhất cho toàn bộ các cửa sổ Swing trong LAB 3.
 * Sử dụng Windows Look and Feel và bảng màu tiết chế, không phụ thuộc thư viện ngoài.
 */
public final class UiTheme {

    public static final Color BG_APP = new Color(245, 246, 248);       // #F5F6F8
    public static final Color BG_SURFACE = Color.WHITE;                // #FFFFFF
    public static final Color BG_READONLY = new Color(249, 250, 251);  // #F9FAFB
    public static final Color TEXT_PRIMARY = new Color(36, 36, 36);    // #242424
    public static final Color TEXT_SECONDARY = new Color(102, 102, 102); // #666666
    public static final Color BORDER_COLOR = new Color(216, 220, 226); // #D8DCE2
    public static final Color GRID_COLOR = new Color(230, 233, 238);

    public static final Color ACCENT = new Color(49, 95, 134);         // #315F86
    public static final Color ACCENT_HOVER = new Color(39, 77, 110);   // #274D6E
    public static final Color BTN_HOVER = new Color(238, 242, 246);    // #EEF2F6

    public static final Color SUCCESS = new Color(24, 121, 78);        // Xanh lá cho thành công / VALID
    public static final Color ERROR = new Color(180, 35, 24);          // Đỏ cho cảnh báo / thất bại / INVALID

    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 19);
    public static final Font FONT_SECTION = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_LABEL = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_INPUT = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_BUTTON_PRIMARY = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_TABLE = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_TABLE_HEADER = new Font("Segoe UI", Font.BOLD, 12);

    private static boolean lafInstalled;

    private UiTheme() {
    }

    /** Cài đặt Windows Look and Feel một lần duy nhất cùng font mặc định cho hộp thoại. */
    public static synchronized void installLookAndFeel() {
        if (lafInstalled) {
            return;
        }
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Giữ Look and Feel mặc định nếu không nạp được System L&F
        }
        UIManager.put("OptionPane.messageFont", FONT_LABEL);
        UIManager.put("OptionPane.buttonFont", FONT_BUTTON);
        UIManager.put("OptionPane.background", BG_APP);
        UIManager.put("Panel.background", BG_APP);
        lafInstalled = true;
    }

    public static void styleFrame(JFrame frame) {
        frame.getContentPane().setBackground(BG_APP);
        frame.setMinimumSize(frame.getPreferredSize());
    }

    public static void styleSectionPanel(JPanel panel) {
        panel.setBackground(BG_SURFACE);
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
    }

    public static void styleStatusPanel(JPanel panel) {
        panel.setBackground(BG_SURFACE);
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
    }

    public static void styleTitle(JLabel label) {
        label.setFont(FONT_TITLE);
        label.setForeground(TEXT_PRIMARY);
    }

    public static void styleSubtitle(JLabel label) {
        label.setFont(FONT_SUBTITLE);
        label.setForeground(TEXT_SECONDARY);
    }

    public static void styleSectionTitle(JLabel label) {
        label.setFont(FONT_SECTION);
        label.setForeground(TEXT_PRIMARY);
    }

    public static void styleFieldLabel(JLabel label) {
        label.setFont(FONT_LABEL);
        label.setForeground(TEXT_PRIMARY);
    }

    public static void styleSecondaryLabel(JLabel label) {
        label.setFont(FONT_SUBTITLE);
        label.setForeground(TEXT_SECONDARY);
    }

    public static void stylePrimaryButton(JButton button) {
        applyButtonStyle(button, ACCENT, ACCENT_HOVER, Color.WHITE, ACCENT_HOVER, FONT_BUTTON_PRIMARY);
    }

    public static void styleSecondaryButton(JButton button) {
        applyButtonStyle(button, BG_SURFACE, BTN_HOVER, TEXT_PRIMARY, BORDER_COLOR, FONT_BUTTON);
    }

    private static void applyButtonStyle(JButton button, Color normalBg, Color hoverBg,
            Color fg, Color borderColor, Font font) {
        button.setFont(font);
        button.setForeground(fg);
        button.setBackground(normalBg);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Border normalBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, 1),
                BorderFactory.createEmptyBorder(4, 12, 4, 12));
        button.setBorder(normalBorder);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setBackground(hoverBg);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normalBg);
            }
        });
    }

    public static void styleTextField(JTextField field) {
        field.setFont(FONT_INPUT);
        field.setForeground(TEXT_PRIMARY);
        field.setBackground(BG_SURFACE);
        field.setCaretColor(TEXT_PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));
    }

    public static void styleComboBox(JComboBox<?> combo) {
        combo.setFont(FONT_INPUT);
        combo.setForeground(TEXT_PRIMARY);
        combo.setBackground(BG_SURFACE);
    }

    public static void styleTextArea(JTextArea area, JScrollPane scroll, boolean readOnly, boolean wrapWords) {
        area.setFont(FONT_INPUT);
        area.setForeground(readOnly ? TEXT_SECONDARY : TEXT_PRIMARY);
        area.setBackground(readOnly ? BG_READONLY : BG_SURFACE);
        area.setCaretColor(TEXT_PRIMARY);
        area.setLineWrap(true);
        area.setWrapStyleWord(wrapWords);
        area.setMargin(new Insets(6, 8, 6, 8));
        if (scroll != null) {
            scroll.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
            scroll.getViewport().setBackground(readOnly ? BG_READONLY : BG_SURFACE);
        }
    }

    public static void styleTable(JTable table, JScrollPane scroll) {
        table.setFont(FONT_TABLE);
        table.setForeground(TEXT_PRIMARY);
        table.setBackground(BG_SURFACE);
        table.setRowHeight(28);
        table.setGridColor(GRID_COLOR);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setIntercellSpacing(new Dimension(1, 1));
        table.setSelectionBackground(new Color(227, 236, 244));
        table.setSelectionForeground(TEXT_PRIMARY);
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        if (header != null) {
            header.setFont(FONT_TABLE_HEADER);
            header.setForeground(TEXT_PRIMARY);
            header.setBackground(BG_APP);
            header.setReorderingAllowed(false);
            header.setPreferredSize(new Dimension(header.getPreferredSize().width, 32));
            header.setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public java.awt.Component getTableCellRendererComponent(JTable tbl, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
                    super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
                    setBackground(BG_APP);
                    setForeground(TEXT_PRIMARY);
                    setFont(FONT_TABLE_HEADER);
                    int align = SwingConstants.LEFT;
                    if (column == 0 || column == 5) {
                        align = SwingConstants.CENTER;
                    } else if (column == 4) {
                        align = SwingConstants.RIGHT;
                    }
                    setHorizontalAlignment(align);
                    setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createMatteBorder(0, 0, 1, 1, BORDER_COLOR),
                            BorderFactory.createEmptyBorder(4, 8, 4, 8)));
                    return this;
                }
            });
        }

        if (scroll != null) {
            scroll.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
            scroll.getViewport().setBackground(BG_SURFACE);
        }
    }

    public static void applyHistoryTableColumns(JTable table) {
        if (table.getColumnModel().getColumnCount() != 6) {
            return;
        }
        int[] widths = {55, 175, 120, 115, 110, 105};
        DefaultTableCellRenderer leftPad = createCellRenderer(SwingConstants.LEFT);
        DefaultTableCellRenderer centerPad = createCellRenderer(SwingConstants.CENTER);
        DefaultTableCellRenderer rightPad = createCellRenderer(SwingConstants.RIGHT);

        for (int i = 0; i < widths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }
        table.getColumnModel().getColumn(0).setCellRenderer(centerPad);
        table.getColumnModel().getColumn(1).setCellRenderer(leftPad);
        table.getColumnModel().getColumn(2).setCellRenderer(leftPad);
        table.getColumnModel().getColumn(3).setCellRenderer(leftPad);
        table.getColumnModel().getColumn(4).setCellRenderer(rightPad);
        table.getColumnModel().getColumn(5).setCellRenderer(centerPad);
    }

    private static DefaultTableCellRenderer createCellRenderer(int align) {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(align);
        r.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        return r;
    }

    public static void setStatusNormal(JLabel label, String text) {
        label.setFont(FONT_SUBTITLE);
        label.setForeground(TEXT_SECONDARY);
        label.setText(text);
    }

    public static void setStatusSuccess(JLabel label, String text) {
        label.setFont(FONT_SUBTITLE);
        label.setForeground(SUCCESS);
        label.setText(text);
    }

    public static void setStatusError(JLabel label, String text) {
        label.setFont(FONT_SUBTITLE);
        label.setForeground(ERROR);
        label.setText(text);
    }
}
