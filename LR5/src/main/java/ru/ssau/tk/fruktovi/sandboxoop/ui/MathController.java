package ru.ssau.tk.fruktovi.sandboxoop.ui;


import ru.ssau.tk.fruktovi.sandboxoop.functions.*;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.fruktovi.sandboxoop.ui.InputThings.DoubleNum;
import ru.ssau.tk.fruktovi.sandboxoop.ui.InputThings.IntNum;
import ru.ssau.tk.fruktovi.sandboxoop.ui.SpecialFunctions.CosineFunction;
import ru.ssau.tk.fruktovi.sandboxoop.ui.SpecialFunctions.SinusFunction;
import ru.ssau.tk.fruktovi.sandboxoop.ui.SpecialFunctions.TangentFunction;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

public class MathController extends JDialog {
    private final JComboBox<String> functionComboBox;
    private final JTextField leftBoundField;
    private final JTextField rightBoundField;
    private final JTextField pointsCountField;
    private final Map<String, MathFunction> functionMap;
    final int PANEL_ROWS = 5;
    final int PANEL_COLUMNS = 2;
    private final TabulatedFunctionFactory factory;
    private TabulatedFunction tabulatedFunction;
    JFrame frame = new JFrame();

    public MathController(JFrame frame, TabulatedFunctionFactory factory) {
        super(frame, true);
        this.factory = new LinkedListTabulatedFunctionFactory();
        this.functionMap = createFunctionMap();
        frame.setTitle("Создать табулированную функцию");
        setSize(600, 400);
        setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(PANEL_ROWS, PANEL_COLUMNS)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(200, 0, new Color(0x435565), 0, getHeight(), new Color(0x435565));

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JLabel functionLabel = new JLabel("Выберите функцию:");
        functionLabel.setForeground(new Color(0x636D7D));
        functionComboBox = new JComboBox<>(functionMap.keySet().toArray(new String[0]));
        JLabel leftBoundLabel = new JLabel("Левая граница:");
        leftBoundLabel.setForeground(new Color(0x636D7D));
        leftBoundField = new JTextField();
        ((AbstractDocument) leftBoundField.getDocument()).setDocumentFilter(new DoubleNum());
        JLabel rightBoundLabel = new JLabel("Правая граница:");
        rightBoundLabel.setForeground(new Color(0x636D7D));
        rightBoundField = new JTextField();
        ((AbstractDocument) rightBoundField.getDocument()).setDocumentFilter(new DoubleNum());
        JLabel pointsCountLabel = new JLabel("Количество точек:");
        pointsCountLabel.setForeground(new Color(0x636D7D));
        pointsCountField = new JTextField();
        ((AbstractDocument) pointsCountField.getDocument()).setDocumentFilter(new IntNum());

        panel.add(functionLabel);
        panel.add(functionComboBox);
        panel.add(leftBoundLabel);
        panel.add(leftBoundField);
        panel.add(rightBoundLabel);
        panel.add(rightBoundField);
        panel.add(pointsCountLabel);
        panel.add(pointsCountField);

        JButton createButton = new RoundedButton("создать", new Color(0x636D7D));
        createButton.addActionListener(new CreateFunctionListener());

        add(panel, BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);
        panel.setVisible(true);
    }

    private Map<String, MathFunction> createFunctionMap() {
        Map<String, MathFunction> map = new TreeMap<>();
        map.put("Квадратичная функция", new SqrFunction());
        map.put("Тождественная функция", new IdentityFunction());
        map.put("Функция константы 0", new ZeroFunction());
        map.put("Функция константы 1", new UnitFunction());
        map.put("Синус", new SinusFunction());
        map.put("Косинус", new CosineFunction());
        map.put("Тангенс", new TangentFunction());
        return map;
    }

    public static class RoundedButton extends JButton {
        public RoundedButton(String label, Color textColor) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(textColor);
            setBackground(new Color(0xCCDBE2));
            setFont(new Font("MerriWeather", Font.PLAIN, 16));
        }


        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            super.paintComponent(g);
        }
    }

    private class CreateFunctionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String selectedFunctionName = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = functionMap.get(selectedFunctionName);

                double leftX = Double.parseDouble(leftBoundField.getText());
                double rightX = Double.parseDouble(rightBoundField.getText());
                int pointsCount = Integer.parseInt(pointsCountField.getText());

                if (leftX >= rightX) {
                    throw new IllegalArgumentException("Левая граница должна быть меньше правой.");
                }
                if (pointsCount < 2) {
                    throw new IllegalArgumentException("Количество точек должно быть больше 1.");
                }
                double[] xValues = new double[pointsCount];
                double[] yValues = new double[pointsCount];
                double step = (rightX - leftX) / (pointsCount - 1);
                for (int i = 0; i < pointsCount; i++) {
                    xValues[i] = leftX + i * step;
                    yValues[i] = selectedFunction.apply(xValues[i]);
                }

                tabulatedFunction = factory.create(xValues, yValues);
                JOptionPane.showMessageDialog(MathController.this, "Функция создана!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MathController.this, "Некорректный ввод!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(MathController.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public TabulatedFunction getTabulatedFunction() {
        return tabulatedFunction;
    }
}

