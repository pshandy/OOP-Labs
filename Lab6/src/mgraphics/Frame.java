package mgraphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mfigures.MCircle;
import mfigures.MEllipse;
import mfigures.MFigure;
import mfigures.MLine;
import mfigures.MRectangle;
import mfigures.MRhombus;
import mfigures.MSquare;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Frame extends JFrame {
	
	/* Размеры экрана */
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    
    /* Размеры панелей */
    private static final int DRAW_PANEL_WIDTH = 800;
    private static final int DRAW_PANEL_HEIGHT = 450;
    private static final int CONTROL_PANEL_WIDTH = 800;
    private static final int CONTROL_PANEL_HEIGHT = 150;
    
    /* Панели */
    private JPanel drawPanel;
    private JPanel controlPanel;
    
    /* Хранилище фигур */
    private static final int N = 20;
    private MFigure[] figures = new MFigure[N];
    
    /* Названия фигур */
    private static final String[] figuresNames = new String[]
            {"Все", "Круг", "Прямоугольник", "Эллипс", "Квадрат", "Линия", "Ромб"};
    
    /* Текстовые поля */
    private HintTextField xTextField;
    private HintTextField yTextField;
    
    /* Комбо-боксы */
    private JComboBox figuresComboBox;
    
    /* Прочее */
    private boolean isHidden = true;
    
    
    public Frame() {
        /*							Начальные настройки							*/
        setTitle("Sixth Laboratory");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setFocusable(true);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Иконка окна */
        ImageIcon brush = new ImageIcon("images/brush.png");
        setIconImage(brush.getImage());

        /*							Панель отрисовки							*/
        drawPanel = new JPanel();
        drawPanel.setBackground(new Color(224, 195, 252));
        drawPanel.setPreferredSize(new Dimension(DRAW_PANEL_WIDTH, DRAW_PANEL_HEIGHT));
        drawPanel.setLayout(null);
        getContentPane().add(drawPanel, BorderLayout.CENTER);

        /*							Панель управления							*/
        controlPanel = new JPanel();
        controlPanel.setBackground(new Color(116, 122, 199));
        controlPanel.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT));
        controlPanel.setLayout(null);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);
        
        /* Создать случайные фигуры */
        JButton createButton = new JButton("Создать");
        createButton.setBounds(10, 11, 111, 23);
        controlPanel.add(createButton);
        createButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		Random rand = new Random();
        		for (int i = 0; i < N; i++) {
        			int j = rand.nextInt(6);	
        			figures[i] = 
        			j == 0 ? new MCircle() :
        			j == 1 ? new MEllipse() :
        			j == 2 ? new MLine() :
        			j == 3 ? new MRectangle() :
        			j == 4 ? new MRhombus() : new MSquare();
        		}
        	}
        });
        createButton.setFocusable(false);
        
        /* Удалить фигуры */
        JButton deleteButton = new JButton("Уничтожить");
        deleteButton.setBounds(10, 47, 111, 23);
        controlPanel.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < N; i++) {
					figures[i] = null;
				}
				isHidden = true;
				repaint();
			}
			
		});
        deleteButton.setFocusable(false);
        
        /* Отобразить все фигуры */
        JButton showButton = new JButton("Показать");
        showButton.setBounds(10, 81, 111, 23);
        controlPanel.add(showButton);
        showButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (figures[0] == null) {
					throwNoChosenFigureMessage();
					return ;
				}
				isHidden = false;
				repaint();
			}
        	
        });
        showButton.setFocusable(false);
        
        /* Стереть фигуры */
        JButton hideButton = new JButton("Стереть");
        hideButton.setBounds(10, 116, 111, 23);
        controlPanel.add(hideButton);
        hideButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isHidden = true;
				repaint();
			}
			
		});
        hideButton.setFocusable(false);
        
        /*							Перемещение в новую точку								*/
        JButton moveButton = new JButton("Переместить");
        moveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (figures[0] == null) {
					throwNoChosenFigureMessage();
					return ;
				}
				if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField})) {
					throwIncorrectNumberMessage();
					return ;
				}
				
				switch ((String)figuresComboBox.getSelectedItem()) {
				
				case "Все":
					for (var f: figures) {
						f.setX(Integer.valueOf(xTextField.getText()));
						f.setY(Integer.valueOf(yTextField.getText()));
					}
					break ;
				case "Круг":
					for (var f: figures) {
						if (f instanceof MCircle) {
							f.setX(Integer.valueOf(xTextField.getText()));
							f.setY(Integer.valueOf(yTextField.getText()));
						}
					}
					break ;
				case "Прямоугольник":
					for (var f: figures) {
						if (f instanceof MRectangle) {
							f.setX(Integer.valueOf(xTextField.getText()));
							f.setY(Integer.valueOf(yTextField.getText()));
						}
					}
					break ;
				case "Эллипс":
					for (var f: figures) {
						if (f instanceof MEllipse) {
							f.setX(Integer.valueOf(xTextField.getText()));
							f.setY(Integer.valueOf(yTextField.getText()));
						}
					}
					break ;
				case "Квадрат":
					for (var f: figures) {
						if (f instanceof MSquare) {
							f.setX(Integer.valueOf(xTextField.getText()));
							f.setY(Integer.valueOf(yTextField.getText()));
						}
					}
					break ;
				case "Линия":
					for (var f: figures) {
						if (f instanceof MLine) {
							f.setX(Integer.valueOf(xTextField.getText()));
							f.setY(Integer.valueOf(yTextField.getText()));
						}
					}
					break ;
				case "Ромб":
					for (var f: figures) {
						if (f instanceof MRhombus) {
							f.setX(Integer.valueOf(xTextField.getText()));
							f.setY(Integer.valueOf(yTextField.getText()));
						}
					}
					break ;
				};
				repaint();
			}
			
		});
        moveButton.setMargin(new Insets(1,1,1,1));
        moveButton.setFocusable(false);
        moveButton.setBounds(142, 47, 111, 23);
        controlPanel.add(moveButton);
        
        figuresComboBox = new JComboBox(figuresNames);
        figuresComboBox.setBounds(142, 11, 111, 22);
        controlPanel.add(figuresComboBox);
        
        xTextField = new HintTextField("X");
        xTextField.setBounds(142, 82, 111, 22);
        controlPanel.add(xTextField);
        
        yTextField = new HintTextField("Y");
        yTextField.setBounds(142, 117, 111, 22);
        controlPanel.add(yTextField);
        
        /*							Применение уникального метода								*/
        JButton uniqueMethodButton = new JButton("Уник-й метод");
        uniqueMethodButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (var f: figures) {
					if (f instanceof MEllipse)
						((MEllipse) f).swapR();
				}
				repaint();
			}
			
		});
        uniqueMethodButton.setMargin(new Insets(1, 1, 1, 1));
        uniqueMethodButton.setFocusable(false);
        uniqueMethodButton.setBounds(271, 11, 111, 23);
        controlPanel.add(uniqueMethodButton);
        
        
        /*							Перемещение клавиатурой								*/
        
        JButton moveOnButton = new JButton("Движение");
        moveOnButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                requestFocus();
        	}
        });
        moveOnButton.setMargin(new Insets(1, 1, 1, 1));
        moveOnButton.setFocusable(false);
        moveOnButton.setBounds(271, 47, 111, 23);
        controlPanel.add(moveOnButton);
        
        this.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (figures[0] == null)
					return ;
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					for (var f: figures)
						f.move(0, -10);
					break;
				case KeyEvent.VK_DOWN:
					for (var f: figures)
						f.move(0, 10);
					break;
				case KeyEvent.VK_LEFT:
					for (var f: figures)
						f.move(-10, 0);
					break;
				case KeyEvent.VK_RIGHT:
					for (var f: figures)
						f.move(10, 0);
					break;
				};
				repaint();
			}
		});
             
    }
    
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	if (!isHidden) {
    		for (var f: figures) {
    			f.draw((Graphics2D) g);
    		}
    	}
    }
    
	/* Проверяет, является ли содержимое полей положительным числом */
	private boolean isPositiveNumber(HintTextField[] params) {
		for (HintTextField str: params) {
			if (!str.getText().matches("[0-9]+") && str != null)
				return false;
		}
		return true;
	}
	
    /* Ошибка о заполненности массива */
	private void throwArrayIsFilledMessage() {
		JOptionPane.showMessageDialog(null, "В массиве нет места", "Ошибка!", JOptionPane.PLAIN_MESSAGE);
	}
	
    /* Ошибка о неправильных данных */
	private void throwIncorrectNumberMessage() {
		JOptionPane.showMessageDialog(null, "Некорректные данные", "Ошибка!", JOptionPane.PLAIN_MESSAGE);
	}
	
    /* Ошибка о невыбранной фигуре */
	private void throwNoChosenFigureMessage() {
		JOptionPane.showMessageDialog(null, "В массиве нет фигур!", "Ошибка!", JOptionPane.PLAIN_MESSAGE);
	}
	
}