package com.school.bank_java.command;

import org.springframework.context.ApplicationContext;


public interface Command {
	public void execute(ApplicationContext ctx); 
}
