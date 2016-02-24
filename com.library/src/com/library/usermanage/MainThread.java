package com.library.usermanage;

import com.library.userdata.UserData;


public class MainThread {

	protected UserButtons buttons_1;
	protected BottomButtons buttons_2;
	protected JudgeText text_2;
	public MainThread(){
		buttons_1 = new UserButtons();
		buttons_2 = new BottomButtons();
	    text_2 = new JudgeText();
		new UserData(buttons_1,buttons_2,text_2);
	}
}
