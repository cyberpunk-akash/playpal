package playpal;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

public class Chatbot extends JFrame implements KeyListener{

	JPanel p=new JPanel();
	JTextArea dialog=new JTextArea(20,50);
	JTextArea input=new JTextArea(1,50);
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
	
	// String[][] chatBot={
	// 	//standard greetings
	// 	{"hi","hello","hola","ola","howdy"},
	// 	{"hi","hello","hey"},
	// 	//question greetings
	// 	{"how are you","how r you","how r u","how are u"},
	// 	{"good","doing well"},
	// 	//yes
	// 	{"yes"},
	// 	{"no","NO","NO!!!!!!!"},
	// 	//default
	// 	{"shut up","you're bad","noob","stop talking",
	// 	"(michael is unavailable, due to LOL)"}
	// };
	String initialReply = "Hi! I am PlayPal. What would you like to know more about? \n \tPlease choose the number of the query you would like to know more about.\n" + 
						"\t1] About PlayPal\n" + 
						"\t2] Events\n" + 
						"\t3] Tournaments\n" + 
						"\t4] Equipments\n" + 
						"\t5] User Profile\n"+
                                                "\t6] Other";

	String[][] chatBot={

	//standard greeting
	{"hi","heloo","hello","how r u","how are you","how r you","how r u","how are u","hi how are you","hi how r u "}, 
	{initialReply},



	
	// 1st answer
	{"1"},
	{"The objective of PlayPal is to spread awareness about fitness sports and \n" +
	"\t promote outdoor activities. PlayPal aims to connect sports and fitness \n" + " \tenthusiasts."},

	{"2"},
	{"An event can be created by any user who is logged in. The user will\n"+"\t mention the type of sports and date on which he plans to play and \n" + "\t other users can view the event and decide if they want to participate or not.\n"+"Some people also ask:-(Please enter query number) \n"+"e1]Are there any restrictions to participate in an event?\n"},

	{"3"},
	{"Tournaments can be created by any user who is logged in. If a user \n " + " \t wishes to participate in some other tournament, he can do so by paying \n" + 
	"\t the participation fee, if there is one, and can also win the prize amount.\n"+"Do you have more questions?(Please enter the query number)\n"+"t1]Is there money refund option if I dont attend the tournament?\n"},

	{"4"},
	{"Equipments can be borrowed or rented. A logged in user can put up\n" + "\t equipments like bat, football, etc., for other people to rent and will receive\n" +
                "\ta small fee for it. Other logged in users can view these equipments \n" + "\t and use them after renting them.\n"+
                "Do you have any more questions(Please enter the query number)\n"+"q1]My equipment was not returned to me.What do I do?\n"+
                "q2]My equipment was returned damaged.What do I do?\n"},

	{"5"},
	{"User profile shows the user his details like name, age, Body Mass Index \n" + "\t (BMI) and events and tournaments created and participated by the user.\n"+"Do you have more questions?(Please select query number)\n u1]What is BMI?\n"},
        {"6"},
	{"To know more you can mail us at playpalhdtk0707@gmail.com!\n"},
        
        {"e1"},
	{"To be able to participate in an event you must be within \nthe required age limit\n"},
        
        {"t1"},
	{"No. There is no refund option. After confirming a seat it is\n it is expected that you participate!"},
        
	{"thank you","Thanks for your help!","thank u"},
	{"It was a pleasure helping you. Hope you have fun using PlayPal."},
        
        {"q1"},
	{"Sorry to hear that you had to face this issue! :( Don't worry we got you.\n Write your complaint to us at playpalhkdt0707@gmail.com and we\n shall investigate"},

        {"q2"},
	{"Sorry to hear that you had to face this issue! :( \nPlayPal is not responsible for damage to any equipment borrowed or rented\n"+
                " but we can surely get you in touch with your rentee\n"+"Write to us at playpalhkdt0707@gmail.com."},
        {"u1"},
	{"Body Mass Index (BMI) is a measurement of a person’s weight with \n respect to his or her height. It is more of an indicator than a direct measurement\n of a person’s total body fat."},

	{"Sorry, I didn't get you. Could you please repeat that?"}


	};
	
	public static void main(String[] args){
		new Chatbot();
	}
	
	public Chatbot(){
		super("Chat Bot");
		setSize(600,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this);
	
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(255,200,0));
		add(p);
		
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);
			//-----grab quote-----------
			String quote=input.getText();
			input.setText("");
			addText("-->You:\t"+quote);
			quote.trim();
			while(
				quote.charAt(quote.length()-1)=='!' ||
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?'
			){
				quote=quote.substring(0,quote.length()-1);
			}
			quote.trim();
			byte response=0;
			/*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/
			//-----check for matches----
			int j=0;//which group we're checking
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("\n-->PlayPal\t"+chatBot[(j*2)+1][r]);
				}
				j++;
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}
			
			//-----default--------------
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n-->PlayPal\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}
	
	public void keyTyped(KeyEvent e){}
	
	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}
	
	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
	}
}