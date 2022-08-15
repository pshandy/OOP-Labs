package mgraphics;

import mfigures.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*
Пользователь должен:
- Создавать несколько объектов разных классов с помощью разных
конструкторов (создать случайно, создать с заданными параметрами). При
нажатии на кнопку создания выводить сообщение.
- Уметь перемещать все объекты одного класса разом и работать с
отдельным объектом (например, объект можно выбрать их списка и затем
его переместить).
- Уметь удалять выбранный объект и объекты одного класса.
- Уметь вызывать дополнительные функции выбранного объекта и
объектов одного класса.
 */

public class Frame extends JFrame {

    /* Размер экрана */
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    /* Размеры панелей */
    private static final int DRAW_PANEL_WIDTH = 800;
    private static final int DRAW_PANEL_HEIGHT = 450;
    private static final int CONTROL_PANEL_WIDTH = 800;
    private static final int CONTROL_PANEL_HEIGHT = 150;

    /* Стандартные фоны */
    private static final Font hFont = new Font("Tahoma", Font.BOLD, 16);
    private static final Font h1Font = new Font("Tahoma", Font.PLAIN, 12);

    /* Массивы-хранилища фигур @expandable */
    private static final int N = 10;
    private MCircle[] circles = new MCircle[N];
    private MRectangle[] rectangles = new MRectangle[N];
    private MEllipse[] ellipses = new MEllipse[N];
    private MSquare[] squares = new MSquare[N];
    private MLine[] lines = new MLine[N];
    private MRhombus[] rhombuses = new MRhombus[N];
    
    /* Выбранные фигуры */
    private MCircle chosenCircle;
    private MRectangle chosenRectangle;
    private MEllipse chosenEllipse;
    private MSquare chosenSquare;
    private MLine chosenLine;
    private MRhombus chosenRhombus;

    /* Названия фигур @expandable */
    private static final String[] figures = new String[]
            {"Круг", "Прямоугольник", "Эллипс", "Квадрат", "Линия", "Ромб"};
    
    /* Текстовые поля @expanadable*/
    private HintTextField xTextField;
    private HintTextField yTextField;
    private HintTextField rTextField;
    private HintTextField widthTextField;
    private HintTextField heigthTextField;
    private HintTextField moveXTextField;
    private HintTextField moveYTextField;

    /* Кнопки */
    private JButton createButton;
    
    /* Панели */
    private JPanel drawPanel;
    private JPanel controlPanel;
    private JPanel panel;
    private JPanel applyToClassPanel;
    
    /* Выпадающие списки */
    private JComboBox comboBox;
    private JLabel choseClassLabel;
    private JComboBox functionsComboBox;
    
    /* Прочее */
    private JScrollPane objListScroll;
    private JList objList;
    private JLabel figuresList;
    
    /* Создание главного экрана */
    public Frame() {

        /*							Начальные настройки							*/
        setTitle("Fifth Laboratory");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
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
        
        /* 							Создание фигур 								*/
		choseClassLabel = new JLabel("Выбор класса:");
		choseClassLabel.setBounds(34, 11, 129, 22);
		choseClassLabel.setFont(hFont);
		controlPanel.add(choseClassLabel);
        
        comboBox = new JComboBox(figures);
        comboBox.setBounds(181, 11, 118, 22);
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(24, 44, 275, 95);
        controlPanel.add(panel);
        
        /* Выбор фигуры @expandable */
        comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				panel.removeAll();
				switch ((String)e.getItem()) {
				case "Круг": showCircleProperties(); break;
				case "Прямоугольник": showRectangleProperties(); break;
				case "Эллипс": showEllipseProperties(); break;
				case "Квадрат": showSquareProperties(); break;
				case "Линия": showLineProperties(); break;
				case "Ромб": showRhombusProperties(); break;
				};
				repaint();
			}
  
        });
        controlPanel.add(comboBox);
        
		figuresList = new JLabel("Список фигур:");
		figuresList.setFont(new Font("Tahoma", Font.BOLD, 16));
		figuresList.setBounds(375, 11, 129, 22);
		controlPanel.add(figuresList);
        
        objListScroll = new JScrollPane();
		objListScroll.setBounds(336, 44, 199, 95);
		objListScroll.setPreferredSize(new Dimension(175, 150));
		controlPanel.add(objListScroll);
		
		/*							Применить к классу 							*/
		JLabel applyToClassLabel = new JLabel("Применить к классу");
		applyToClassLabel.setFont(hFont);
		applyToClassLabel.setBounds(579, 12, 180, 20);
		controlPanel.add(applyToClassLabel);
		
		applyToClassPanel = new JPanel();
		applyToClassPanel.setBounds(579, 77, 180, 32);
		controlPanel.add(applyToClassPanel);
		applyToClassPanel.setLayout(null);
		
		functionsComboBox = new JComboBox(new String[] {"Переместить", "Удалить", "Изменить"});
		functionsComboBox.setBounds(579, 44, 180, 22);
		functionsComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				applyToClassPanel.removeAll();
				showFunctions();
				repaint();
			}
  
        });
		controlPanel.add(functionsComboBox);
		
		JButton applyToClassButton = new JButton("Применить");
		applyToClassButton.setBounds(606, 117, 129, 22);
		controlPanel.add(applyToClassButton);
		applyToClassButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applyToClass();
				repaint();
			}
		});
		
		showFunctions();
		showCircleProperties();
    }

	private void showFunctions() {
		switch ((String)functionsComboBox.getSelectedItem()) {
		case "Переместить": 
			moveXTextField = new HintTextField("X");
			moveXTextField.setBounds(24, 5, 60, 20);
			applyToClassPanel.add(moveXTextField);
			
			moveYTextField = new HintTextField("Y");
			moveYTextField.setBounds(94, 5, 60, 20);
			applyToClassPanel.add(moveYTextField);
			break;
		case "Удалить": break;
		case "Изменить": 
			moveXTextField = new HintTextField("Width");
			moveXTextField.setBounds(24, 5, 60, 20);
			applyToClassPanel.add(moveXTextField);
			
			moveYTextField = new HintTextField("Height");
			moveYTextField.setBounds(94, 5, 60, 20);
			applyToClassPanel.add(moveYTextField);
			break;
		};
    }
    
    private void applyToClass() {
		
		String classString = ((String)comboBox.getSelectedItem());
		String funcString = ((String)functionsComboBox.getSelectedItem());

		switch (funcString) {
		case "Переместить":
			
			if (!isNumber(new HintTextField[] {moveXTextField, moveYTextField})) {
				throwIncorrectNumberMessage();
				return ;
			}
			
	    	int x = Integer.valueOf(moveXTextField.getText());
			int y = Integer.valueOf(moveYTextField.getText());
			
			if (classString.equals(figures[0]))
				for (var f: circles) {
					if (f != null)
						f.move(x, y, this);
				}
			else if (classString.equals(figures[1]))
				for (var f: rectangles) {
					if (f != null)
						f.move(x, y, this); 
				}
			else if (classString.equals(figures[2]))
				for (var f: ellipses) {
					if (f != null)
						f.move(x, y, this);
				}
			else if (classString.equals(figures[3]))
				for (var f: squares) {
					if (f != null)
						f.move(x, y, this);
				}
			else if (classString.equals(figures[4]))
				for (var f: lines) {
					if (f != null)
						f.move(x, y, this);
				}
			else if (classString.equals(figures[5]))
				for (var f: rhombuses) {
					if (f != null)
						f.move(x, y, this);
				}
			break ;
		case "Удалить":
			if (classString.equals(figures[0]))
				for (var f: circles) {
            		for (int i = 0; i < N; i++)
            			if (circles[i] == f) {
            				circles[i] = null;
            			}
				}
			else if (classString.equals(figures[1]))
				for (var f: rectangles) {
            		for (int i = 0; i < N; i++)
            			if (rectangles[i] == f) {
            				rectangles[i] = null;
            			}
				}
			else if (classString.equals(figures[2]))
				for (var f: ellipses) {
            		for (int i = 0; i < N; i++)
            			if (ellipses[i] == f) {
            				ellipses[i] = null;
            			}
				}
			else if (classString.equals(figures[3]))
				for (var f: squares) {
            		for (int i = 0; i < N; i++)
            			if (squares[i] == f) {
            				squares[i] = null;
            			}
				}
			else if (classString.equals(figures[4]))
				for (var f: lines) {
            		for (int i = 0; i < N; i++)
            			if (lines[i] == f) {
            				lines[i] = null;
            			}
				}
			else if (classString.equals(figures[5]))
				for (var f: rhombuses) {
            		for (int i = 0; i < N; i++)
            			if (rhombuses[i] == f) {
            				rhombuses[i] = null;
            			}
				}
			break ;
		case "Изменить":
			if (!isPositiveNumber(new HintTextField[] {moveXTextField, moveYTextField})) {
				throwIncorrectNumberMessage();
				return ;
			}
			if (classString.equals(figures[1])) {
				for (var f: rectangles) {
					if (f != null) {
						f.setWidth(Integer.valueOf(moveXTextField.getText()));
						f.setHeight(Integer.valueOf(moveYTextField.getText()));
					}
				}
			}
		};
    }
    
    private void showCircleProperties() {
    	
    	objList = new JList(circles);
		objList.setCellRenderer(new MListCellRenderer());
		objListScroll.setViewportView(objList);
		objList.setFixedCellHeight(15);
		objList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		objList.setBackground(new Color(224, 195, 252));
		
		objList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (objList.getSelectedValue() != null)
					chosenCircle = (MCircle)objList.getSelectedValue();
				else
					return ;
				xTextField.setText(String.valueOf(chosenCircle.getX()));
				yTextField.setText(String.valueOf(chosenCircle.getY()));
				rTextField.setText(String.valueOf(chosenCircle.getR()));;
			}	
		});

        xTextField = new HintTextField("x");
        xTextField.setFont(h1Font);
        xTextField.setBounds(10, 11, 71, 20);
        panel.add(xTextField);
        
        yTextField = new HintTextField("y");
        yTextField.setFont(h1Font);
        yTextField.setBounds(10, 42, 71, 20);
        panel.add(yTextField);
        
        rTextField = new HintTextField("r");
        rTextField.setFont(h1Font);
        rTextField.setBounds(95, 11, 71, 20);
        panel.add(rTextField);
                
        createButton = new JButton("Создать");
        createButton.setFocusable(false);
        createButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField, rTextField})) {
        			throwIncorrectNumberMessage();
        			return ;
        		} else 	
        			for (int i = 0; i < N; i++)
        				if (circles[i] == null) {
        					circles[i] = new MCircle(Integer.valueOf(xTextField.getText()),
        												Integer.valueOf(yTextField.getText()),
        												Integer.valueOf(rTextField.getText()));
        					repaint();
        					return ;
        				}
        		throwArrayIsFilledMessage();
        	}
        });
        createButton.setBounds(176, 61, 89, 23);
        panel.add(createButton);
        
        JButton randomButton = new JButton("Рандом");
        randomButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		for (int i = 0; i < N; i++)
        			if (circles[i] == null) {
        				circles[i] = new MCircle();
        				repaint();
        				return ;
        			}
        		throwArrayIsFilledMessage();
        	}
        });
        randomButton.setFocusable(false);
        randomButton.setBounds(176, 36, 89, 23);
        panel.add(randomButton);
        
        JButton deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenCircle == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		for (var f: circles) {
            		for (int i = 0; i < N; i++)
            			if (circles[i] == chosenCircle) {
            				circles[i] = null;
            				chosenCircle = null;
            				repaint();
            				return ;
            			}
        		}
        	}
        });
        deleteButton.setFocusable(false);
        deleteButton.setBounds(176, 11, 89, 23);
        panel.add(deleteButton);
        
        JButton changeButton = new JButton("Изменить");
        changeButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenCircle == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField, rTextField})) {
        			throwIncorrectNumberMessage();
        			return ;
        		}
        		chosenCircle.setX(Integer.valueOf(xTextField.getText())); 
        		chosenCircle.setY(Integer.valueOf(yTextField.getText())); 
        		chosenCircle.setR(Integer.valueOf(rTextField.getText())); 
        		repaint();;
        	}
        });
        changeButton.setMargin(new Insets(1,1,1,1));
        changeButton.setFocusable(false);
        changeButton.setBounds(10, 65, 71, 23);
        panel.add(changeButton);
    }
    
    private void showRectangleProperties() {
    	
    	objList = new JList(rectangles);
		objList.setCellRenderer(new MListCellRenderer());
		objListScroll.setViewportView(objList);
		objList.setFixedCellHeight(15);
		objList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		objList.setBackground(new Color(224, 195, 252));
		
		objList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (objList.getSelectedValue() != null)
					chosenRectangle = (MRectangle)objList.getSelectedValue();
				else
					return ;
				xTextField.setText(String.valueOf(chosenRectangle.getX()));
				yTextField.setText(String.valueOf(chosenRectangle.getY()));
				widthTextField.setText(String.valueOf(chosenRectangle.getWidth()));
				heigthTextField.setText(String.valueOf(chosenRectangle.getHeight()));
			}
			
		});
    	
    	xTextField = new HintTextField("x");
        xTextField.setFont(h1Font);
        xTextField.setBounds(10, 11, 71, 20);
        panel.add(xTextField);
        
        yTextField = new HintTextField("y");
        yTextField.setFont(h1Font);
        yTextField.setBounds(10, 42, 71, 20);
        panel.add(yTextField);
        
        widthTextField = new HintTextField("width");
        widthTextField.setFont(h1Font);
        widthTextField.setBounds(91, 11, 71, 20);
        panel.add(widthTextField);
        
        heigthTextField = new HintTextField("height");
        heigthTextField.setFont(h1Font);
        heigthTextField.setBounds(91, 42, 71, 20);
        panel.add(heigthTextField);
        
        createButton = new JButton("Создать");
        createButton.setFocusable(false);
        createButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField, widthTextField, heigthTextField})) {
        			throwIncorrectNumberMessage();
        			return ;
        		} else 	
        			for (int i = 0; i < N; i++) 
        				if (rectangles[i] == null) {
        					rectangles[i] = new MRectangle(Integer.valueOf(xTextField.getText()),
        												Integer.valueOf(yTextField.getText()),
        												Integer.valueOf(widthTextField.getText()),
        												Integer.valueOf(heigthTextField.getText()));
        					repaint();
        					return ;
        				}
        			throwArrayIsFilledMessage();
        	}
        });
        createButton.setBounds(176, 61, 89, 23);
        panel.add(createButton);
        
        JButton randomButton = new JButton("Рандом");
        randomButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		for (int i = 0; i < N; i++)
        			if (rectangles [i] == null) {
        				rectangles[i] = new MRectangle();
        				repaint();
        				return ;
        			}
        		throwArrayIsFilledMessage();
        	}
        });
        randomButton.setFocusable(false);
        randomButton.setBounds(176, 36, 89, 23);
        panel.add(randomButton); 
        
        JButton deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenRectangle == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		for (var f: rectangles) {
            		for (int i = 0; i < N; i++)
            			if (rectangles[i] == chosenRectangle) {
            				rectangles[i] = null;
            				chosenRectangle = null;
            				repaint();
            				return ;
            			}
        		}
        	}
        });
        deleteButton.setFocusable(false);
        deleteButton.setBounds(176, 11, 89, 23);
        panel.add(deleteButton);
        
        JButton changeButton = new JButton("Изменить");
        changeButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenRectangle == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField, widthTextField, heigthTextField})) {
        			throwIncorrectNumberMessage();
        			return ;
        		}
        		chosenRectangle.setX(Integer.valueOf(xTextField.getText())); 
        		chosenRectangle.setY(Integer.valueOf(yTextField.getText())); 
        		chosenRectangle.setWidth(Integer.valueOf(widthTextField.getText())); 
        		chosenRectangle.setHeight(Integer.valueOf(heigthTextField.getText())); 
        		repaint();
        	}
        });
        changeButton.setMargin(new Insets(1,1,1,1));
        changeButton.setFocusable(false);
        changeButton.setBounds(10, 65, 71, 23);
        panel.add(changeButton); 
    }
    
    private void showSquareProperties() {
    	
    	objList = new JList(squares);
		objList.setCellRenderer(new MListCellRenderer());
		objListScroll.setViewportView(objList);
		objList.setFixedCellHeight(15);
		objList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		objList.setBackground(new Color(224, 195, 252));
		
		objList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (objList.getSelectedValue() != null)
					chosenSquare = (MSquare)objList.getSelectedValue();
				else
					return ;
				xTextField.setText(String.valueOf(chosenSquare.getX()));
				yTextField.setText(String.valueOf(chosenSquare.getY()));
				widthTextField.setText(String.valueOf(chosenSquare.getWidth()));
			}
			
		});
    	
        xTextField = new HintTextField("x");
        xTextField.setFont(h1Font);
        xTextField.setBounds(10, 11, 71, 20);
        panel.add(xTextField);
        
        yTextField = new HintTextField("y");
        yTextField.setFont(h1Font);
        yTextField.setBounds(10, 42, 71, 20);
        panel.add(yTextField);
        
        widthTextField = new HintTextField("width");
        widthTextField.setFont(h1Font);
        widthTextField.setBounds(91, 11, 71, 20);
        panel.add(widthTextField);
        
        createButton = new JButton("Создать");
        createButton.setFocusable(false);
        createButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField, widthTextField})) {
        			throwIncorrectNumberMessage();
        			return ;
        		} else 	
        			for (int i = 0; i < N; i++)
        				if (squares[i] == null) {
        					squares[i] = new MSquare(Integer.valueOf(xTextField.getText()),
        												Integer.valueOf(yTextField.getText()),
        												Integer.valueOf(widthTextField.getText()));
        					repaint();
        					return ;
        				}
        			throwArrayIsFilledMessage();
        	}
        });
        createButton.setBounds(176, 61, 89, 23);
        panel.add(createButton);
        
        JButton randomButton = new JButton("Рандом");
        randomButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		for (int i = 0; i < N; i++)
        			if (squares[i] == null) {
        				squares[i] = new MSquare();
        				repaint();
        				return ;
        			}
        		throwArrayIsFilledMessage();
        	}
        });
        randomButton.setFocusable(false);
        randomButton.setBounds(176, 36, 89, 23);
        panel.add(randomButton); 
        
        JButton deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenSquare == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		for (var f: squares) {
            		for (int i = 0; i < N; i++)
            			if (squares[i] == chosenSquare) {
            				squares[i] = null;
            				chosenSquare = null;
            				repaint();
            				return ;
            			}
        		}
        	}
        });
        deleteButton.setFocusable(false);
        deleteButton.setBounds(176, 11, 89, 23);
        panel.add(deleteButton);
        
        JButton changeButton = new JButton("Изменить");
        changeButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenSquare == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField, widthTextField})) {
        			throwIncorrectNumberMessage();
        			return ;
        		}
        		chosenSquare.setX(Integer.valueOf(xTextField.getText())); 
        		chosenSquare.setY(Integer.valueOf(yTextField.getText())); 
        		chosenSquare.setWidth(Integer.valueOf(widthTextField.getText())); 
        		repaint();
        	}
        });
        changeButton.setMargin(new Insets(1,1,1,1));
        changeButton.setFocusable(false);
        changeButton.setBounds(10, 65, 71, 23);
        panel.add(changeButton); 
    }
    
    protected void showRhombusProperties() {
    	objList = new JList(rhombuses);
		objList.setCellRenderer(new MListCellRenderer());
		objListScroll.setViewportView(objList);
		objList.setFixedCellHeight(15);
		objList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		objList.setBackground(new Color(224, 195, 252));
		
		objList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (objList.getSelectedValue() != null)
					chosenRhombus = (MRhombus)objList.getSelectedValue();
				else
					return ;
				xTextField.setText(String.valueOf(chosenRhombus.getX()));
				yTextField.setText(String.valueOf(chosenRhombus.getY()));
				widthTextField.setText(String.valueOf(chosenRhombus.getWidth()));
				heigthTextField.setText(String.valueOf(chosenRhombus.getHeight()));
			}
			
		});
    	
    	xTextField = new HintTextField("x");
        xTextField.setFont(h1Font);
        xTextField.setBounds(10, 11, 71, 20);
        panel.add(xTextField);
        
        yTextField = new HintTextField("y");
        yTextField.setFont(h1Font);
        yTextField.setBounds(10, 42, 71, 20);
        panel.add(yTextField);
        
        widthTextField = new HintTextField("width");
        widthTextField.setFont(h1Font);
        widthTextField.setBounds(91, 11, 71, 20);
        panel.add(widthTextField);
        
        heigthTextField = new HintTextField("height");
        heigthTextField.setFont(h1Font);
        heigthTextField.setBounds(91, 42, 71, 20);
        panel.add(heigthTextField);
        
        createButton = new JButton("Создать");
        createButton.setFocusable(false);
        createButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField, widthTextField, heigthTextField})) {
        			throwIncorrectNumberMessage();
        			return ;
        		} else 	
        			for (int i = 0; i < N; i++) 
        				if (rhombuses[i] == null) {
        					rhombuses[i] = new MRhombus(Integer.valueOf(xTextField.getText()),
        												Integer.valueOf(yTextField.getText()),
        												Integer.valueOf(widthTextField.getText()),
        												Integer.valueOf(heigthTextField.getText()));
        					repaint();
        					return ;
        				}
        			throwArrayIsFilledMessage();
        	}
        });
        createButton.setBounds(176, 61, 89, 23);
        panel.add(createButton);
        
        JButton randomButton = new JButton("Рандом");
        randomButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		for (int i = 0; i < N; i++)
        			if (rhombuses [i] == null) {
        				rhombuses[i] = new MRhombus();
        				repaint();
        				return ;
        			}
        		throwArrayIsFilledMessage();
        	}
        });
        randomButton.setFocusable(false);
        randomButton.setBounds(176, 36, 89, 23);
        panel.add(randomButton); 
        
        JButton deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenRhombus == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		for (var f: rhombuses) {
            		for (int i = 0; i < N; i++)
            			if (rhombuses[i] == chosenRhombus) {
            				rhombuses[i] = null;
            				chosenRhombus = null;
            				repaint();
            				return ;
            			}
        		}
        	}
        });
        deleteButton.setFocusable(false);
        deleteButton.setBounds(176, 11, 89, 23);
        panel.add(deleteButton);
        
        JButton changeButton = new JButton("Изменить");
        changeButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenRhombus == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField, widthTextField, heigthTextField})) {
        			throwIncorrectNumberMessage();
        			return ;
        		}
        		chosenRhombus.setX(Integer.valueOf(xTextField.getText())); 
        		chosenRhombus.setY(Integer.valueOf(yTextField.getText())); 
        		chosenRhombus.setWidth(Integer.valueOf(widthTextField.getText())); 
        		chosenRhombus.setHeight(Integer.valueOf(heigthTextField.getText())); 
        		repaint();
        	}
        });
        changeButton.setMargin(new Insets(1,1,1,1));
        changeButton.setFocusable(false);
        changeButton.setBounds(10, 65, 71, 23);
        panel.add(changeButton); 
	}

	protected void showLineProperties() {
    	objList = new JList(lines);
		objList.setCellRenderer(new MListCellRenderer());
		objListScroll.setViewportView(objList);
		objList.setFixedCellHeight(15);
		objList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		objList.setBackground(new Color(224, 195, 252));
		
		objList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (objList.getSelectedValue() != null)
					chosenLine = (MLine)objList.getSelectedValue();
				else
					return ;
				xTextField.setText(String.valueOf(chosenLine.getX()));
				yTextField.setText(String.valueOf(chosenLine.getY()));
				widthTextField.setText(String.valueOf(chosenLine.getX2()));
				heigthTextField.setText(String.valueOf(chosenLine.getY2()));
			}
			
		});
    	
    	xTextField = new HintTextField("x");
        xTextField.setFont(h1Font);
        xTextField.setBounds(10, 11, 71, 20);
        panel.add(xTextField);
        
        yTextField = new HintTextField("y");
        yTextField.setFont(h1Font);
        yTextField.setBounds(10, 42, 71, 20);
        panel.add(yTextField);
        
        widthTextField = new HintTextField("x2");
        widthTextField.setFont(h1Font);
        widthTextField.setBounds(91, 11, 71, 20);
        panel.add(widthTextField);
        
        heigthTextField = new HintTextField("y2");
        heigthTextField.setFont(h1Font);
        heigthTextField.setBounds(91, 42, 71, 20);
        panel.add(heigthTextField);
        
        createButton = new JButton("Создать");
        createButton.setFocusable(false);
        createButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField, widthTextField, heigthTextField})) {
        			throwIncorrectNumberMessage();
        			return ;
        		} else 	
        			for (int i = 0; i < N; i++) 
        				if (lines[i] == null) {
        					lines[i] = new MLine(Integer.valueOf(xTextField.getText()),
        												Integer.valueOf(yTextField.getText()),
        												Integer.valueOf(widthTextField.getText()),
        												Integer.valueOf(heigthTextField.getText()));
        					repaint();
        					return ;
        				}
        			throwArrayIsFilledMessage();
        	}
        });
        createButton.setBounds(176, 61, 89, 23);
        panel.add(createButton);
        
        JButton randomButton = new JButton("Рандом");
        randomButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		for (int i = 0; i < N; i++)
        			if (lines[i] == null) {
        				lines[i] = new MLine();
        				repaint();
        				return ;
        			}
        		throwArrayIsFilledMessage();
        	}
        });
        randomButton.setFocusable(false);
        randomButton.setBounds(176, 36, 89, 23);
        panel.add(randomButton); 
        
        JButton deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenLine == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		for (var f: lines) {
            		for (int i = 0; i < N; i++)
            			if (lines[i] == chosenLine) {
            				lines[i] = null;
            				chosenLine = null;
            				repaint();
            				return ;
            			}
        		}
        	}
        });
        deleteButton.setFocusable(false);
        deleteButton.setBounds(176, 11, 89, 23);
        panel.add(deleteButton);
        
        JButton changeButton = new JButton("Изменить");
        changeButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenLine == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField, widthTextField, heigthTextField})) {
        			throwIncorrectNumberMessage();
        			return ;
        		}
        		chosenLine.setX(Integer.valueOf(xTextField.getText())); 
        		chosenLine.setY(Integer.valueOf(yTextField.getText())); 
        		chosenLine.setX2(Integer.valueOf(widthTextField.getText())); 
        		chosenLine.setY2(Integer.valueOf(heigthTextField.getText())); 
        		repaint();
        	}
        });
        changeButton.setMargin(new Insets(1,1,1,1));
        changeButton.setFocusable(false);
        changeButton.setBounds(10, 65, 71, 23);
        panel.add(changeButton); 
	}

	protected void showEllipseProperties() {
    	objList = new JList(ellipses);
		objList.setCellRenderer(new MListCellRenderer());
		objListScroll.setViewportView(objList);
		objList.setFixedCellHeight(15);
		objList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		objList.setBackground(new Color(224, 195, 252));
		
		objList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (objList.getSelectedValue() != null)
					chosenEllipse = (MEllipse)objList.getSelectedValue();
				else
					return ;
				xTextField.setText(String.valueOf(chosenEllipse.getX()));
				yTextField.setText(String.valueOf(chosenEllipse.getY()));
				widthTextField.setText(String.valueOf(chosenEllipse.getR()));
				heigthTextField.setText(String.valueOf(chosenEllipse.getR2()));
			}
			
		});
    	
    	xTextField = new HintTextField("x");
        xTextField.setFont(h1Font);
        xTextField.setBounds(10, 11, 71, 20);
        panel.add(xTextField);
        
        yTextField = new HintTextField("y");
        yTextField.setFont(h1Font);
        yTextField.setBounds(10, 42, 71, 20);
        panel.add(yTextField);
        
        widthTextField = new HintTextField("R1");
        widthTextField.setFont(h1Font);
        widthTextField.setBounds(91, 11, 71, 20);
        panel.add(widthTextField);
        
        heigthTextField = new HintTextField("R2");
        heigthTextField.setFont(h1Font);
        heigthTextField.setBounds(91, 42, 71, 20);
        panel.add(heigthTextField);
        
        createButton = new JButton("Создать");
        createButton.setFocusable(false);
        createButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField, widthTextField, heigthTextField})) {
        			throwIncorrectNumberMessage();
        			return ;
        		} else 	
        			for (int i = 0; i < N; i++) 
        				if (ellipses[i] == null) {
        					ellipses[i] = new MEllipse(Integer.valueOf(xTextField.getText()),
        												Integer.valueOf(yTextField.getText()),
        												Integer.valueOf(widthTextField.getText()),
        												Integer.valueOf(heigthTextField.getText()));
        					repaint();
        					return ;
        				}
        			throwArrayIsFilledMessage();
        	}
        });
        createButton.setBounds(176, 61, 89, 23);
        panel.add(createButton);
        
        JButton randomButton = new JButton("Рандом");
        randomButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		for (int i = 0; i < N; i++)
        			if (ellipses [i] == null) {
        				ellipses[i] = new MEllipse();
        				repaint();
        				return ;
        			}
        		throwArrayIsFilledMessage();
        	}
        });
        randomButton.setFocusable(false);
        randomButton.setBounds(176, 36, 89, 23);
        panel.add(randomButton); 
        
        JButton deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenEllipse == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		for (var f: ellipses) {
            		for (int i = 0; i < N; i++)
            			if (ellipses[i] == chosenEllipse) {
            				ellipses[i] = null;
            				ellipses = null;
            				repaint();
            				return ;
            			}
        		}
        	}
        });
        deleteButton.setFocusable(false);
        deleteButton.setBounds(176, 11, 89, 23);
        panel.add(deleteButton);
        
        JButton changeButton = new JButton("Изменить");
        changeButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenEllipse == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		if (!isPositiveNumber(new HintTextField[] {xTextField, yTextField, widthTextField, heigthTextField})) {
        			throwIncorrectNumberMessage();
        			return ;
        		}
        		chosenEllipse.setX(Integer.valueOf(xTextField.getText())); 
        		chosenEllipse.setY(Integer.valueOf(yTextField.getText())); 
        		chosenEllipse.setR(Integer.valueOf(widthTextField.getText())); 
        		chosenEllipse.setR2(Integer.valueOf(heigthTextField.getText())); 
        		repaint();
        	}
        });
        changeButton.setMargin(new Insets(1,1,1,1));
        changeButton.setFocusable(false);
        changeButton.setBounds(10, 65, 71, 23);
        panel.add(changeButton); 
        
        JButton rotateButton = new JButton("Повернуть");
        rotateButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (chosenEllipse == null) {
        			throwNoChosenFigureMessage();
        			return ;
        		}
        		chosenEllipse.swapR();
        		repaint();
        	}
        });
        rotateButton.setMargin(new Insets(1,1,1,1));
        rotateButton.setFocusable(false);
        rotateButton.setBounds(85, 65, 80, 23);
        panel.add(rotateButton); 
	}

    /* Отрисовка фигур @expandable */
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLUE);

        /* Отрисовка окружностей */
        for (var f : circles)
            if (f != null)
                f.draw(g2D);

        /* Отрисовка прямоугольников */
        for (var f : rectangles)
            if (f != null)
                f.draw(g2D);

        /* Отрисовка линий */
        for (var f : lines)
            if (f != null)
                f.draw(g2D);

        /* Отрисовка квадратов */
        for (var f : squares)
            if (f != null)
                f.draw(g2D);

        /* Отрисовка ромбов */
        for (var f : rhombuses)
            if (f != null)
                f.draw(g2D);
        
        /* Отрисовка эллипсов */
        for (var f : ellipses)
            if (f != null)
                f.draw(g2D);

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
		JOptionPane.showMessageDialog(null, "Сначала выберите фигуру", "Ошибка!", JOptionPane.PLAIN_MESSAGE);
	}
	
	/* Проверяет, является ли содержимое полей числом */
	private boolean isNumber(HintTextField[] params) {
		for (HintTextField str: params) {
			if (!str.getText().matches("^-?[0-9]+") && str != null)
				return false;
		}
		return true;
	}
	
	/* Проверяет, является ли содержимое полей положительным числом */
	private boolean isPositiveNumber(HintTextField[] params) {
		for (HintTextField str: params) {
			if (!str.getText().matches("[0-9]+") && str != null)
				return false;
		}
		return true;
	}
}