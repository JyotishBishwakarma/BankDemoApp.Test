package com.iso.nextgen.test.navigator;


import com.iso.nextgen.test.ui.common.core.PageBase;
import com.iso.nextgen.test.ui.common.navigator.Navigator;


public interface EFDNavigator extends Navigator
{
	PageBase switchWindow(PageBase target, String windowName);
	String getCurrentUrl();
	String getWindow(int index);
	void closeWindow(String windowName);
    void sleep(int t) throws InterruptedException;
   
}