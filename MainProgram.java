import java.awt.Color;
import java.awt.event.KeyEvent;

public class MainProgram {

	public static void main(String[] args) {

		EZ.initialize(1024, 700); // set up EZ and make 1024X700 window

		EZ.setBackgroundColor(new Color(150, 150, 150));

		EZImage carPicture = EZ.addImage("simple-travel-car-top_view.png", 512, 350); // loads car images, assigns to
																						// variable carPicture
		
		EZImage item1 = EZ.addImage("Thinker.png", 100, 650); // load 1st item picture at (100,650),assigns to variable
																// item1
		
		EZImage item2 = EZ.addImage("Sandvich.png", 950, 90); // load 2nd item pic at (950,90), assign to variable item2
		
		EZImage item3 = EZ.addImage("DamnFourthCE.png", 990, 600); // load 3rd item pic at (990,600),assign to variable
																	// item3
		
		EZImage obstacle1 = EZ.addImage("beardexpense.png", 200, 200); // load 1st obstacle pic at (200,200), assign to
																		// variable obstacle1
		
		EZImage obstacle2 = EZ.addImage("Sakura.png", 990, 300); // load 2nd obstacle pic at (990,300), assign to
																	// variable obstacle2
		
		EZImage building1 = EZ.addImage("buildinguno.png", 200, 400); // load 1st building pic at (200,400) w/ variable
																		// building1
		
		EZImage building2 = EZ.addImage("building2.png", 400, 200); // load 2nd building pic at (400,200) w/ variable
																	// building2
		
		EZImage building3 = EZ.addImage("factory.png", 800, 600); // load 3rd building pic at (800,600) w/ variable
																	// building3
		
		EZImage building4 = EZ.addImage("school.png", 300, 600); // load 4th building pic at (300,600) w/ variable
																	// building4
		
		EZImage building5 = EZ.addImage("cityhall.png", 850, 250); // load 5th building pic at (850,250) w/ variable
																	// building5
		
		EZSound crashSound = EZ.addSound("wariogocrashboomouch.wav"); // load sound for crashing into building
		
		EZSound item1Collect = EZ.addSound("Phoenix - objection.wav"); // load unique sound for 1st item
		
		EZSound item2Collect = EZ.addSound("sandvich.wav"); // load unique sound for 2nd item
		
		EZSound item3Collect = EZ.addSound("DamnFourthCE.wav"); // load unique sound for 3rd item
		
		EZSound obstacle1Hit = EZ.addSound("beardedCrashOuch.wav"); // load crash sound for 1st obstacle
		
		EZSound obstacle2Hit = EZ.addSound("sakuratruckhit.wav"); // load crash sound for 2nd obstacle

		
		int fontsize = 50; // set up text at top of screen w/ corresponding font, font size
		
		EZText text = EZ.addText(512, 50, "GET THE STUFF, DON'T DIE", new Color(0, 0, 100), fontsize);
		
		text.setFont("tf2build.ttf");

		
		while (true) { // setup of movement controls of car
			
			int itemsCollected = 0; // int variable itemCollected set to 0
			
			boolean canControl = true; // determines if player can control car
			
			while (canControl = true) {
				if (EZInteraction.isKeyDown("w")) { // if w is pressed
					
					carPicture.moveForward(10); // move car forward 10 px
				}
				if (EZInteraction.isKeyDown("s")) { // if s is pressed
					
					carPicture.moveForward(-10); // move car backwards 10 px
				}
				if (EZInteraction.isKeyDown("a")) { // if a key pressed
					
					carPicture.turnLeft(5); // turn car 5 degrees left
				}
				if (EZInteraction.isKeyDown("d")) { // if d key pressed
					
					carPicture.turnRight(5); // turn car 5 degrees right
				}
				int posX = carPicture.getXCenter(); // finding the x and y values of the center of the car image and
													// assigning them to integers posX and posY respectively
				int posY = carPicture.getYCenter();

				if (item1.isPointInElement(posX, posY)) { // check if car drove over 1st item, if so then...
					
					item1Collect.play(); // play item-specific sound
					
					text.setMsg("Thinker get!"); // set the text at the top to item-specific message
					
					item1.moveForward(-200); // move item1 200px left away from the playfield
					
					itemsCollected = itemsCollected + 1; // increment itemCollected by 1
				}

				if (item2.isPointInElement(posX, posY)) { // check if car drove over 2nd item, if so then...
					
					item2Collect.play(); // same as when item1 is collected, but moved right 1200px off the playfield
					
					text.setMsg("mmm sandvich");
					
					item2.moveForward(1200);
					
					itemsCollected = itemsCollected + 1;
				}
				if (item3.isPointInElement(posX, posY)) { // same as the process after item2 is drove over
					
					item3Collect.play();
					
					text.setMsg("Damn 4th Chaos Emerald!");
					
					item3.moveForward(1200);
					
					itemsCollected = itemsCollected + 1;
				}
				if (obstacle1.isPointInElement(posX, posY)) { // if player is within 1st obstacle
					
					obstacle1Hit.play(); // play corresponding crash sound
					
					EZ.addImage("beardedisdown.png", 512, 350); // load in corresponding game over image at (512,350)
				}
				if (obstacle2.isPointInElement(posX, posY)) { // if player is within 2nd obstacle
					
					obstacle2Hit.play(); // do same process as w/ obstacle1
					
					EZ.addImage("sakuradie.png", 512, 350); // but with corresponding crash sound and image
				}
				while (obstacle1.isPointInElement(posX, posY)) { // while player is within obstacle1
					
					text.setMsg("bruh why you do that"); // change text to corresponding message
					
					canControl = false; // set canControl to false
				}
				while (obstacle2.isPointInElement(posX, posY)) { // while player is within obstacle2
					
					text.setMsg("saga start?"); // do the same thing as w/ obstacle1
					
					canControl = false; // but with obstacle-specific text
				}
				if ((building1.isPointInElement(posX, posY)) || (building2.isPointInElement(posX, posY))
						|| (building3.isPointInElement(posX, posY)) || (building4.isPointInElement(posX, posY))
						|| (building5.isPointInElement(posX, posY))) { // if car drove into any buildings
					
					crashSound.play(); // play crash sound
				}
				while ((building1.isPointInElement(posX, posY)) || (building2.isPointInElement(posX, posY))
						|| (building3.isPointInElement(posX, posY)) || (building4.isPointInElement(posX, posY))
						|| (building5.isPointInElement(posX, posY))) { // while car is stuck in any of the buildings
					
					text.setMsg("you ded :'( exit the game"); // set text to corresponding message
					
					canControl = false; // set canControl to false
					
					EZ.addImage("youfailsad.jpg", 512, 350); // load in game over image at (512,350)
				}
				while (itemsCollected == 3) { // while itemsCollected == 3 / all items collected
					
					EZ.addImage("yourewinner.png", 512, 350); // load in victory image
				}

				if (canControl = false)
					;
				{ // if canControl = false, disable player control of car
					if (EZInteraction.isKeyDown("w")) {
						carPicture.moveForward(0); // pressing w moves the car 0 px
					}
					if (EZInteraction.isKeyDown("s")) { // pressing s moves the car 0 px
						carPicture.moveForward(0);
					}
					if (EZInteraction.isKeyDown("a")) { // pressing a turns car 0 degrees left
						carPicture.turnLeft(0);
					}
					if (EZInteraction.isKeyDown("d")) { // pressing d turns car 0 degrees right
						carPicture.turnRight(0);
					}

				}

				// sets the bounds of the play area so the car can't drive off the screen for
				// eternity
				if (posX > 1024) { // if posX of car exceeds 1024 px (right side of window)
					
					posX = 1024; // set posX at 1024 px/edge of window
					
					carPicture.translateTo(posX, posY); // move the car to new posX
				}
				if (posX < 0) { // if posX of car less than 0 px (left side of window)
					
					posX = 0; // set posX at 0 px/edge of window
					
					carPicture.translateTo(posX, posY); // move the car to new posX
				}
				if (posY < 0) { // if posY of car less than 0 px (top side of window)
					
					posY = 0; // set posY at 0 px/edge of window
					
					carPicture.translateTo(posX, posY); // move the car to new posY
				}
				if (posY > 700) { // if posY of car more than 700 px (bottom side of window)
					
					posY = 700; // set posY at 700 px/edge of window
					
					carPicture.translateTo(posX, posY); // move car to new posY
				}

				EZ.refreshScreen(); // make sure the game screen actually updates so things move and stuff
			}
		}

	}
}
