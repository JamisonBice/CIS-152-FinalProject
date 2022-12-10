import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import GUI.MainMenuPanel;

/**************************************************************
* Name        : Start.java
* Author      : Jamison Bice
* Created     : 09/12/2022
* Course      : CIS 152 Data Structures
* Version     : 1.0
* OS          : Windows 10
* IDE         : Eclipse 2022-06
* Copyright   : This is my own original work based on
*               specifications issued by our instructor
* Description : An app that acts as an shopping list and stores multiple shopping lists
*            Input: List Items and Data
*            Output: Organized Table of input and shopping lists
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access to
* my program.
***************************************************************/

/**
 * @author Jamison Bice
 *
 */
public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame();// Inits
		MainMenuPanel panel = new MainMenuPanel();

		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 10));// Format
		panel.setLayout(new GridLayout(0, 1));

		frame.add(panel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// Basic Requierments for function Frame
		frame.setTitle("ShoppingList");
		frame.setVisible(true);
		frame.setSize(720, 720);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setVisible(true);

	}

}
