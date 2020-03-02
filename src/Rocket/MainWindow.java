package Rocket;

import javax.swing.*;

public class MainWindow extends JFrame{
	private JPanel mainPanel;
	private JButton createRocket;
	private JFrame mainFrame = new JFrame();

	public static void main(String[] args) {
		new MainWindow();
	}

	public MainWindow(){
		/*mainFrame.setSize(300,300);
		mainFrame.setVisible(true);
		mainFrame.setContentPane(mainPanel);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);*/
		FilesReader.readAllData();
		System.out.println(Genetic.createRocket().toString());
		FilesReader.saveSetting();
	}
}
