package ru.ssau.tk.fruktovi.sandboxoop.ui;

import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.fruktovi.sandboxoop.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreatingFactory extends JDialog {
    private final int WIDTH_DIALOG = 400;
    private final int HEIGHT_DIALOG = 250;

    public CreatingFactory(JFrame owner, TabulatedFunctionOperationService factoryService) {
        super(owner, "Настройки", true); // Модальное окно
        setSize(WIDTH_DIALOG, HEIGHT_DIALOG);
        setLocationRelativeTo(null);

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(200, 0, new Color(67, 85, 101), 0, getHeight(), new Color(67, 85, 101));

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JRadioButton arrayFactoryButton = new JRadioButton("<html><font color='#636D7D'>Фабрика на основе массива</font></html>",
                factoryService.getFactory() instanceof ArrayTabulatedFunctionFactory);
        JRadioButton listFactoryButton = new JRadioButton("<html><font color='#636D7D'>Фабрика на основе связного списка</font></html>",
                factoryService.getFactory() instanceof LinkedListTabulatedFunctionFactory);

        ButtonGroup group = new ButtonGroup();
        group.add(arrayFactoryButton);
        group.add(listFactoryButton);

        JButton saveButton = new JButton("<html><font color='#636D7D'>Сохранить</font></html>");
        saveButton.setBackground(new Color(204, 219, 226));
        saveButton.addActionListener(_ -> {
            if (arrayFactoryButton.isSelected()) {
                factoryService.setFactory(new ArrayTabulatedFunctionFactory());
            } else if (listFactoryButton.isSelected()) {
                factoryService.setFactory(new LinkedListTabulatedFunctionFactory());
            }
            dispose();
        });

        gradientPanel.add(arrayFactoryButton);
        gradientPanel.add(listFactoryButton);
        gradientPanel.add(saveButton);

        add(gradientPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
