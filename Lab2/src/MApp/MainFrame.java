package MApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MGraphic.*;

public class MainFrame extends JFrame {
	
	private int FRAME_WIDTH = 1012;
	private int FRAME_HEIGHT = 600;
	
	private JPanel infoPanel;
	private JPanel editPanel;
	private JPanel bottomPanel;
	private JPanel workSpace;
	
	private JList objList;
	private JScrollPane objListScroll;
	
	private JTextField xTextField;
	private JTextField yTextField;
	private JTextField nameTextField;
	private JTextField xMoveTextField;
	private JTextField yMoveTextField;
	private JTextField newWidthTextField;
	private JTextField newHeightTextField;
	
	private Color color = Color.BLUE;
	
	//----
	
	private MClass chosenClass = MClass.values()[0];
	private MFunction chosenFunction = MFunction.values()[0];
	
	private MCircle chosenCircle;
	private MSquare chosenSquare;
	private MRectangle chosenRectangle;
	
	//----
	
	public static final int ARRAY_LENGTH = 10;
	
	private MCircle[] circlesArray = new MCircle[ARRAY_LENGTH];
	private MSquare[] squaresArray = new MSquare[ARRAY_LENGTH];
	private MRectangle[] rectanglesArray = new MRectangle[ARRAY_LENGTH];
	
	public MainFrame() {
		
		/*
		 * ????????? ??????
		 */
		
		setTitle("Second Laboratory");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon brush = new ImageIcon("images/brush.png");
		setIconImage(brush.getImage());
		
		/*
		 * ??????????? ??????
		 */

		workSpace = new JPanel();
		workSpace.setBackground(new Color(224, 195, 252));
		workSpace.setPreferredSize(new Dimension(FRAME_WIDTH, 450));
		workSpace.setLayout(null);
		getContentPane().add(workSpace, BorderLayout.CENTER);

		/*
		 * ?????? ??????
		 */

		bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(116, 122, 199));
		bottomPanel.setPreferredSize(new Dimension(FRAME_WIDTH, 150));
		bottomPanel.setLayout(null);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		/*
		 * ?????? ??????????? ?????????? ?? ???????
		 */
		
		infoPanel = new JPanel();
		infoPanel.setBounds(158, 36, 273, 91);
		bottomPanel.add(infoPanel);
		infoPanel.setLayout(null);
		
		/*
		 * ?????? ??????????? ?????????? ? ????????
		 */
		
		editPanel = new JPanel();
		editPanel.setBounds(863, 27, 123, 112);
		bottomPanel.add(editPanel);
			
		/*
		 * ?????? ???????? ??????????
		 */
		
		JButton circleButton = new JButton("Круг");
		circleButton.setFocusable(false);
		circleButton.setBounds(10, 36, 138, 23);
		
		circleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showCircleParams(false);
			}
			
		});
		
		bottomPanel.add(circleButton);
		
		/*
		 * ?????? ???????? ????????
		 */
		
		JButton squareButton = new JButton("Квадрат");
		squareButton.setFocusable(false);
		squareButton.setBounds(10, 70, 138, 23);
		
		squareButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showSquareParams(false);
			}
			
		});
		
		bottomPanel.add(squareButton);
		
		/*
		 * ?????? ???????? ??????????????
		 */
		
		JButton rectangleButton = new JButton("Прямоугольник");
		rectangleButton.setFocusable(false);
		rectangleButton.setBounds(10, 104, 138, 23);
		
		rectangleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showRectangleParams(false);
			}
			
		});
		
		bottomPanel.add(rectangleButton);
		
		/*
		 * ??????? ??????? ?? ??????
		 */
		
		JComboBox functionComboBox = new JComboBox(MFunction.getAltNames());

		functionComboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				chosenFunction = MFunction.getEnumRepresentation((String) functionComboBox.getSelectedItem());
				showFunctionParams();
			}

		});
		
		showFunctionParams();
		
		functionComboBox.setBounds(677, 104, 176, 23);
		bottomPanel.add(functionComboBox);
		
		/*
		 * ????????? ??? ?????????
		 */
		
		objListScroll = new JScrollPane();
		objListScroll.setBounds(441, 36, 226, 91);
		objListScroll.setPreferredSize(new Dimension(175, 150));
		bottomPanel.add(objListScroll);
		
		/*
		 * ??????? ????? ?? ??????
		 */

		JComboBox classComboBox = new JComboBox(MClass.getAltNames());

		classComboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				chosenClass = MClass.getEnumRepresentation((String) classComboBox.getSelectedItem());
				showClassLists();
			}

		});

		showClassLists();
		
		classComboBox.setBounds(677, 70, 176, 30);
		bottomPanel.add(classComboBox);

		/*
		 * ????????? ? ??????
		 */

		JButton applyToClassButton = new JButton("Применить к классу");

		applyToClassButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				applyToClass();
			}

		});

		applyToClassButton.setFocusable(false);
		applyToClassButton.setBounds(677, 36, 176, 30);
		bottomPanel.add(applyToClassButton);
				
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2D = (Graphics2D) g;
		
		for (MCircle f: circlesArray)
			if (f != null)
				f.show(g2D);
		
		for (MSquare f: squaresArray)
			if (f != null)
				f.show(g2D);
		
		for (MRectangle f: rectanglesArray)
			if (f != null)
				f.show(g2D);
		
		showClassLists();
	}
	
	/*
	 * ---------------------------------------------
	 */
		
	private void applyToClass() {
		
		infoPanel.removeAll();
		
			switch (chosenFunction) {

			case MMOVE:

				if (isNumber(new String[] {xMoveTextField.getText(), yMoveTextField.getText()})) {
					for (int i = 0; i < ARRAY_LENGTH; i++) {
						switch (chosenClass) {
						case MCIRCLE:
							if (chosenCircle != null)
							chosenCircle.move(Integer.valueOf(xMoveTextField.getText()),
										Integer.valueOf(yMoveTextField.getText()));
							chosenCircle = circlesArray[i];
							break;
						case MSQUARE:
							if (chosenSquare != null)
								chosenSquare.move(Integer.valueOf(xMoveTextField.getText()),
										Integer.valueOf(yMoveTextField.getText()));
								chosenSquare = squaresArray[i];
							break;
						case MRECTANGLE:
							if (chosenRectangle != null)
								chosenRectangle.move(Integer.valueOf(xMoveTextField.getText()),
										Integer.valueOf(yMoveTextField.getText()));
								chosenRectangle = rectanglesArray[i];
							break;
						}
					}
					infoPanel.removeAll();
					repaint();
				}
				break;

			case MDELETE:
							
				for (int i = 0; i < ARRAY_LENGTH; i++) {
					switch (chosenClass) {
					case MCIRCLE:
						if (chosenCircle != null)
							delete(chosenCircle);
							chosenCircle = circlesArray[i];
							break;
					case MSQUARE:
						if (chosenSquare != null)
							delete(chosenSquare);
							chosenSquare = squaresArray[i];
							break;
					case MRECTANGLE:
						if (chosenRectangle != null)
							delete(chosenRectangle);
							chosenRectangle = rectanglesArray[i];
							break;
					};
				}
				
				infoPanel.removeAll();
				repaint();
				break;
			
			case MCHANGESIDES:
				for (int i = 0; i < ARRAY_LENGTH; i++)
					if (isPositiveNumber(new String[] {newWidthTextField.getText(), newHeightTextField.getText()})
							&& chosenClass == MClass.MRECTANGLE) {
						if (chosenRectangle != null) {
							chosenRectangle.setWidth(Integer.valueOf(newWidthTextField.getText()));
							chosenRectangle.setHeight(Integer.valueOf(newHeightTextField.getText()));
						}
						chosenRectangle = rectanglesArray[i];
					}
				infoPanel.removeAll();
				repaint();
				break;
			};
	}
	
	private void showClassLists() {
		
		switch (chosenClass) {
		
		case MCIRCLE: 
			objList = new JList(getCirclesNames());
			break;
		case MSQUARE:
			objList = new JList(getSquaresNames());
			break;
		case MRECTANGLE:
			objList = new JList(getRectanglesNames());
			break;
		};
		
		objListScroll.setViewportView(objList);
		objList.setFixedCellHeight(15);
		objList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		objList.setBackground(new Color(224, 195, 252));
		
		objList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if (objList.getSelectedValue() != null) {
					String name = (String) objList.getSelectedValue();
					switch(chosenClass) {
					case MCIRCLE: 
						for (int i = 0; i < ARRAY_LENGTH; i++)
							if (circlesArray[i].getName().equals(name)) {
								chosenCircle = circlesArray[i];
								break;
							}
						showCircleParams(true);
						break;
					case MSQUARE:
						for (int i = 0; i < ARRAY_LENGTH; i++)
							if (squaresArray[i].getName().equals(name)) {
								chosenSquare = squaresArray[i];
								break;
							}
						showSquareParams(true);
						break;
					case MRECTANGLE:
						for (int i = 0; i < ARRAY_LENGTH; i++)
							if (rectanglesArray[i].getName().equals(name)) {
								chosenRectangle = rectanglesArray[i];
								break;
							}
						showRectangleParams(true);
						break;
					};
				}
			}

		});
	}

	private void showFunctionParams() {
		
		editPanel.removeAll();
		editPanel.revalidate();
		editPanel.repaint();
		
		switch (chosenFunction) {
		case MMOVE:
			
			JLabel xMoveLabel = new JLabel("X:    ");
			editPanel.add(xMoveLabel);
			
			xMoveTextField = new JTextField();
			editPanel.add(xMoveTextField);
			xMoveTextField.setColumns(10);
			
			JLabel yMoveLabel = new JLabel("Y:    ");
			editPanel.add(yMoveLabel);
			
			yMoveTextField = new JTextField();
			editPanel.add(yMoveTextField);
			yMoveTextField.setColumns(10);	
			
			break;
			
		case MCHANGESIDES:
			
			JLabel newWidthLabel = new JLabel("Ширина");
			editPanel.add(newWidthLabel);
			
			newWidthTextField = new JTextField();
			editPanel.add(newWidthTextField);
			newWidthTextField.setColumns(10);
			
			JLabel newHeightLabel = new JLabel("Длина");
			editPanel.add(newHeightLabel);
			
			newHeightTextField = new JTextField();
			editPanel.add(newHeightTextField);
			newHeightTextField.setColumns(10);
			
			break;
		
		};

		editPanel.repaint();
	}

	private void showCircleParams(boolean flag) {
		
		infoPanel.removeAll();
		infoPanel.revalidate();
		infoPanel.repaint();
		
		for (Component c: getEssentialParams()) {
			infoPanel.add(c);
		}
		
		JLabel radiusLabel = new JLabel("Radius:");
		radiusLabel.setBounds(167, 37, 49, 14);
		infoPanel.add(radiusLabel);
		
		JTextField radiusTextField = new JTextField();
		radiusTextField.setBounds(215,36, 49, 20);
		infoPanel.add(radiusTextField);
		
		JButton createButton = new JButton("Создать");
		createButton.setBounds(165, 63, 98, 20);
		createButton.setFocusable(false);
		infoPanel.add(createButton);
		
		JButton deleteButton = new JButton("У");
		deleteButton.setBounds(137, 63, 26, 20);
		deleteButton.setFocusable(false);
		deleteButton.setMargin(new Insets(1,1,1,1));
		infoPanel.add(deleteButton);
		
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (chosenCircle != null) {
					delete(chosenCircle);
					chosenCircle = null;
					infoPanel.removeAll();
					repaint();
				}
			}
			
		});
		
		createButton.addActionListener(new ActionListener() {
		
			boolean isChangeable = flag;
			
			@Override
			public void actionPerformed(ActionEvent e) {
							
				String x = xTextField.getText();
				String y = yTextField.getText();
				String radius = radiusTextField.getText();
				
				if (isChangeable == false) {
					
					if (isPositiveNumber(new String[] {x, y, radius}))
						chosenCircle = new MCircle(Integer.valueOf(x), Integer.valueOf(y), 
															Integer.valueOf(radius), color);
					else{
						chosenCircle = new MCircle();
					}
					
				} else if (isPositiveNumber(new String[] {x, y, radius})) {
					chosenCircle.setX(Integer.valueOf(x));
					chosenCircle.setY(Integer.valueOf(y));
					chosenCircle.setR(Integer.valueOf(radius));
				}
				

				xTextField.setText(String.valueOf(chosenCircle.getX()));
				yTextField.setText(String.valueOf(chosenCircle.getY()));
				radiusTextField.setText(String.valueOf(chosenCircle.getR()));
				nameTextField.setText(chosenCircle.getName());
				
				if (isChangeable == false) {
					isChangeable = true;
					add(chosenCircle);
					createButton.setText("Изменить");
				}
				
				repaint();
			}
		});
		
		if (chosenCircle != null && flag == true) {
			createButton.setText("Изменить");
			xTextField.setText(String.valueOf(chosenCircle.getX()));
			yTextField.setText(String.valueOf(chosenCircle.getY()));
			radiusTextField.setText(String.valueOf(chosenCircle.getR()));
			nameTextField.setText(chosenCircle.getName());
		}
				
		infoPanel.repaint();
		
	}
	
	private void showSquareParams(boolean flag) {
		
		infoPanel.removeAll();
		infoPanel.revalidate();
		infoPanel.repaint();
		
		for (Component c: getEssentialParams()) {
			infoPanel.add(c);
		}
		
		JLabel radiusLabel = new JLabel("Width:");
		radiusLabel.setBounds(167, 37, 49, 14);
		infoPanel.add(radiusLabel);
		
		JTextField widthTextField = new JTextField();
		widthTextField.setBounds(215,36, 49, 20);
		infoPanel.add(widthTextField);
		
		JButton createButton = new JButton("Создать");
		createButton.setBounds(165, 63, 98, 20);
		createButton.setFocusable(false);
		infoPanel.add(createButton);
		
		JButton deleteButton = new JButton("У");
		deleteButton.setBounds(137, 63, 26, 20);
		deleteButton.setFocusable(false);
		deleteButton.setMargin(new Insets(1,1,1,1));
		infoPanel.add(deleteButton);
		
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chosenSquare != null) {
					delete(chosenSquare);
					chosenSquare = null;
					infoPanel.removeAll();
					repaint();
				}
			}
			
		});
		
		createButton.addActionListener(new ActionListener() {
				
			boolean isChangeable = flag;
			
			@Override
			public void actionPerformed(ActionEvent e) {
							
				String x = xTextField.getText();
				String y = yTextField.getText();
				String width = widthTextField.getText();
				
				if (isChangeable == false) {
					
					if (isPositiveNumber(new String[] {x, y, width}))
						chosenSquare = new MSquare(Integer.valueOf(x), Integer.valueOf(y), 
								Integer.valueOf(width), color);
					else{
						chosenSquare = new MSquare();
					}
					
				} else if (isPositiveNumber(new String[] {x, y, width})) {
					chosenSquare.setX(Integer.valueOf(x));
					chosenSquare.setY(Integer.valueOf(y));
					chosenSquare.setSide(Integer.valueOf(width));
				}
				

				xTextField.setText(String.valueOf(chosenSquare.getX()));
				yTextField.setText(String.valueOf(chosenSquare.getY()));
				widthTextField.setText(String.valueOf(chosenSquare.getSide()));
				nameTextField.setText(chosenSquare.getName());
								
				if (isChangeable == false) {
					isChangeable = true;
					add(chosenSquare);
					createButton.setText("Изменить");
				}
				
				repaint();
			}
			
		});
		
		if (chosenSquare != null && flag == true) {
			createButton.setText("Изменить");
			xTextField.setText(String.valueOf(chosenSquare.getX()));
			yTextField.setText(String.valueOf(chosenSquare.getY()));
			widthTextField.setText(String.valueOf(chosenSquare.getSide()));
			nameTextField.setText(chosenSquare.getName());
		}
		
		infoPanel.repaint();
		
	}
	
	private void showRectangleParams(boolean flag) {
		
		infoPanel.removeAll();
		infoPanel.revalidate();
		infoPanel.repaint();
		
		for (Component c: getEssentialParams()) {
			infoPanel.add(c);
		}
		
		JLabel widthLabel = new JLabel("Width:");
		widthLabel.setBounds(172, 39, 49, 14);
		infoPanel.add(widthLabel);
		
		JTextField widthTextField = new JTextField();
		widthTextField.setBounds(212, 36, 49, 20);
		infoPanel.add(widthTextField);
		
		JLabel heightLabel = new JLabel("Height");
		heightLabel.setBounds(172, 8, 49, 19);
		infoPanel.add(heightLabel);
		
		JTextField heightTextField = new JTextField();
		heightTextField.setBounds(212, 5, 49, 20);
		infoPanel.add(heightTextField);
		
		JButton createButton = new JButton("Создать");
		createButton.setBounds(165, 63, 98, 20);
		createButton.setFocusable(false);
		infoPanel.add(createButton);
		
		JButton deleteButton = new JButton("У");
		deleteButton.setBounds(137, 63, 26, 20);
		deleteButton.setFocusable(false);
		deleteButton.setMargin(new Insets(1,1,1,1));
		infoPanel.add(deleteButton);
		
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chosenRectangle != null) {
					delete(chosenRectangle);
					chosenRectangle = null;
					infoPanel.removeAll();
					repaint();
				}
			}
			
		});
		
		createButton.addActionListener(new ActionListener() {
			
			boolean isChangeable = flag;
			
			@Override
			public void actionPerformed(ActionEvent e) {
							
				String x = xTextField.getText();
				String y = yTextField.getText();
				String width = widthTextField.getText();
				String height = heightTextField.getText();
				
				if (isChangeable == false) {
					
					if (isPositiveNumber(new String[] {x, y, width, height}))
						chosenRectangle = new MRectangle(Integer.valueOf(x), Integer.valueOf(y), 
								Integer.valueOf(width), Integer.valueOf(height), color);
					else{
						chosenRectangle = new MRectangle();
					}
					
				} else if (isPositiveNumber(new String[] {x, y, width, height})) {
					chosenRectangle.setX(Integer.valueOf(x));
					chosenRectangle.setY(Integer.valueOf(y));
					chosenRectangle.setWidth(Integer.valueOf(width));
					chosenRectangle.setHeight(Integer.valueOf(height));
				}
				
				xTextField.setText(String.valueOf(chosenRectangle.getX()));
				yTextField.setText(String.valueOf(chosenRectangle.getY()));
				widthTextField.setText(String.valueOf(chosenRectangle.getWidth()));
				heightTextField.setText(String.valueOf(chosenRectangle.getHeight()));
				nameTextField.setText(chosenRectangle.getName());
				
				if (isChangeable == false) {
					isChangeable = true;
					add(chosenRectangle);
					createButton.setText("Изменить");
				}
				
				repaint();
			}
			
		});
		
		if (chosenRectangle != null && flag == true) {
			createButton.setText("Изменить");
			xTextField.setText(String.valueOf(chosenRectangle.getX()));
			yTextField.setText(String.valueOf(chosenRectangle.getY()));
			widthTextField.setText(String.valueOf(chosenRectangle.getWidth()));
			heightTextField.setText(String.valueOf(chosenRectangle.getHeight()));
			nameTextField.setText(chosenRectangle.getName());
		}
		
		infoPanel.repaint();
		
	}
	
	/*
	 * ?????????? ????? ??????????
	 */
	
	private Component[] getEssentialParams() {
		
		JLabel xLabel = new JLabel("X:");
		xLabel.setBounds(10, 8, 49, 14);
		
		xTextField = new JTextField();
		xTextField.setBounds(64, 5, 49, 20);
		xTextField.setColumns(10);
		
		JLabel yLabel = new JLabel("Y:");
		yLabel.setBounds(10, 39, 49, 14);
		
		yTextField = new JTextField();
		yTextField.setBounds(64, 36, 49, 20);
		yTextField.setColumns(10);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(10, 66, 49, 14);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(64, 63, 72, 20);
		
		return (new Component[] {xLabel, yLabel, nameLabel,
								 xTextField, yTextField, nameTextField});
	}
	
	/*
	 * ?????????, ???????? ?? ????????? ?????? ??????
	 */
	
	private boolean isNumber(String[] params) {
		
		for (String str: params) {
			if (!str.matches("^-?[0-9]+") && str != null)
				return false;
		}
		
		return true;
	}
	
	/*
	 * ?????????, ???????? ?? ????????? ?????? ????????????? ??????
	 */
	
	private boolean isPositiveNumber(String[] params) {
		
		for (String str: params) {
			if (!str.matches("[0-9]+") && str != null)
				return false;
		}
		
		return true;
	}
	
	/*
	 * ?????? ? ????????
	 */
	
	public void add(MCircle figure) {
		for (int i = 0; i < ARRAY_LENGTH; i++) 
			if (circlesArray[i] == null) {
				circlesArray[i] = figure;
				return ;
			}
		throwArrayIsFilledMessage();
		infoPanel.removeAll();
	}
	
	public void delete(MCircle figure) {
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			if (circlesArray[i] != null && figure.getName().equals(circlesArray[i].getName()))
				circlesArray[i] = null;
		}
	}
	
	public String[] getCirclesNames() {
		String arr[] = new String[ARRAY_LENGTH];
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			if (circlesArray[i] != null && arr[i] == null)
				arr[i] = circlesArray[i].getName();
		}
		return arr;
	}
	
	/*
	 * ????????? ??????? ? ??????
	 */
	
	public void add(MSquare figure) {
		for (int i = 0; i < ARRAY_LENGTH; i++) 
			if (squaresArray[i] == null) {
				squaresArray[i] = figure;
				return ;
			}
		throwArrayIsFilledMessage();
		infoPanel.removeAll();
	}
	
	public void delete(MSquare figure) {
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			if (squaresArray[i] != null && figure.getName().equals(squaresArray[i].getName()))
				squaresArray[i] = null;
		}
	}
	
	public String[] getSquaresNames() {
		String arr[] = new String[ARRAY_LENGTH];
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			if (squaresArray[i] != null && arr[i] == null)
				arr[i] = squaresArray[i].getName();
		}
		return arr;
	}
	
	/*
	 * ????????? ????????????? ? ??????
	 */
	
	public void add(MRectangle figure) {
		for (int i = 0; i < ARRAY_LENGTH; i++) 
			if (rectanglesArray[i] == null) {
				rectanglesArray[i] = figure;
				return ;
			}
		throwArrayIsFilledMessage();
		infoPanel.removeAll();
	}
	
	public void delete(MRectangle figure) {
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			if (rectanglesArray[i] != null && figure.getName().equals(rectanglesArray[i].getName()))
				rectanglesArray[i] = null;
		}
	}
	
	public String[] getRectanglesNames() {
		String arr[] = new String[ARRAY_LENGTH];
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			if (rectanglesArray[i] != null && arr[i] == null)
				arr[i] = rectanglesArray[i].getName();
		}
		return arr;
	}
	
	/*
	 * ?????? ????????? ? ?????????????
	 */
	
	private void throwArrayIsFilledMessage() {
		JOptionPane.showMessageDialog(null, "?????? ?????? ????????.", "??????!", JOptionPane.PLAIN_MESSAGE);
	}
}
