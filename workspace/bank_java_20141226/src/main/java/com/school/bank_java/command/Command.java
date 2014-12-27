package com.school.bank_java.command;

import com.school.bank_java.factory.BankFactory;
import com.school.bank_java.factory.Factory;

public interface Command {
	public void execute(Factory factory);
}
