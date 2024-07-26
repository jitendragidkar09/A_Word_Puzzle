package com.springboot.guesstheword.service;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GameService {

	private String randomlyChoosenWord=null;
	
	private String[] randomWords= {"hashmap","catch","static","void","abstract","volatile","finally","finalize","comparator","comparable","strictfp"};

	private char[] allCharactersOfTheWord;
	
	Random random=new Random();
	
	public GameService() {
		
		randomlyChoosenWord=randomWords[random.nextInt(randomWords.length)];
		//System.out.println("Randomly Choosen Word :"+randomlyChoosenWord);
		allCharactersOfTheWord=new char[randomlyChoosenWord.length()];
	}



	@Override
	public String toString() {

		String ret="";
		for(char c: allCharactersOfTheWord) {
			if(c=='\u0000') {
				ret=ret + "_";
			}
			else {
				ret=ret+c;
			}
			ret=ret + ' ';
		}
		return ret;
	}
	
	public boolean addGuess(char guessedChar) {
		
		boolean isGuessCorrect=false;
		
		//we have to check if the guessedChar(o) present inside the randomlyChoosenWord
		for(int i=0; i<randomlyChoosenWord.length(); i++) {		
			if(guessedChar==randomlyChoosenWord.charAt(i)) {				
				allCharactersOfTheWord[i]=guessedChar;
				isGuessCorrect=true;
			}
		}
		
		return isGuessCorrect;
		
	}
	
	
}
