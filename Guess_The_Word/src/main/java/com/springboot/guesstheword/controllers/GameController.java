package com.springboot.guesstheword.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.guesstheword.service.GameService;
import com.springboot.guesstheword.utils.GameUtils;

@Controller
public class GameController {

	@Autowired
	GameService gameService;
	@Autowired
	GameUtils gameUtils;
	
	@GetMapping("/game_home")
	public String showGameHomePage(@RequestParam(value="guessedChar",required=false) String guessedChar, Model model) {
		String randomWord = gameService.toString();  //_ _ _ _ _ _
		
		if(guessedChar !=null) {
		boolean isGuessCorrect=gameService.addGuess(guessedChar.charAt(0));
			randomWord=gameService.toString(); // h _ _ _ _
			
			if(isGuessCorrect==false) {
				gameUtils.reduceTry();
			}
		}
		//System.out.println("No. of trials remaining : "+gameUtils.getTriesRemaining());
		model.addAttribute("triesLeft",gameUtils.getTriesRemaining());
		model.addAttribute("wordToDisplay", randomWord);
		return "game_home_page";
	}
	@GetMapping("/reload")
	public String reloadGame() {
		gameService = gameUtils.reload();
		
		//reset the tries to initial values
		gameUtils.resetTries();
		return "redirect:/game_home";
	}
	
}
