# Repertoire
## A Digital Flashcard Learning Tool

### Work Assignments
- Aaron
  - Database
  - Web-API

- Dave
  - Project documentation, use cases
  - Login Screen
  - Sound Effects, Text to Speech/Audio

- Tucker
  - General Java client
  - Design

### Target Audience
- [ ] As a language learning support tool
  - With its pre-loaded libraries of language cards, Repertoire serves as an intuitive, enjoyable approach to reinforcement language instruction.  With a study mode - where the user can study virtual flash cards to help with memorizing non-phonetic ideograms - and a game mode - where the user can work on learning ideograms through a flash card game - users are empowered to choose how they learn a second, or subsequent, language.

- [ ] As a platform for general subject learning
  - With its customizable card deck creation, Repertoire can support any type of learning for a student of any subject.  Users can create decks of custom cards for study or play to help them reinforce recall of pertinent facts.  Here's a small set of potential deck sets
    - For the amateur lepidopterist: create a butterfly deck to reinforce your field identification skills
    - For the computer science student: create a decimal to hexadecimal base deck to reinforce your skills at different number bases
    - For the advanced math student: create a derivative rules deck to help memorize how to take those pesky derivatives depending on the expressions being evaluated
    - For the nursing student: create an anatomy deck to help commit assorted organs and bones to memory

### Feature List
- [ ] The application's feature include:
    - a game mode to work on subject mastery
    - a study mode to review your subject at your pace
    - customizable dictionaries, you can study any subject, any time

### Use Cases

- [ ] **Login Screen State**
  - user shall be able to enter their username and password to access the application
  - user shall be able to click on a link to retrieve their username
  - user shall be able to click on a link to reset their password
  - user shall be able to click on a link to create a profile

  - **Login Screen Use Case:** User logs into the application
    - Actor: User
    - Use Case Overview: The user decides to log into the application.  The user is presented with the login screen.  It contains two fields: one is labeled "Username", one is labeled "Password"; it also contains an "OK" button, a "Cancel" button, and three hyperlinks: one to retrieve a username, one to reset a password, and one to create a profile.  The cursor is set to the Username field by default.  The user enters their username using the keyboard.  The user selects the Password field by clicking on it with the mouse or using the Tab key on the keyboard.  The user enters their password using the keyboard.  The user completes the login process by clicking on the OK button or using the Enter key on the keyboard.
    - Trigger: The user decided to log into the application
    - Precondition1: The user has created a profile
    - Basic Flow: This scenario outlines the login process state if the user has an existing profile
	* 1. The user chooses to use the application
	* 2. As the cursor defaults to the Username field, the user enters their username in the Username field
	* 3. The user moves the cursor to the Password field via the mouse or the Tab key
	* 4. The user enters their password in the Password field via the keyboard
	* 5. The Password field is enabled to enter the password via the Enter key.  Alternately, the user can click on the OK button
	* 6. The user is logged into the application
    - Termination Outcome: The user is taken to the main menu screen.

    - Alternative Flow 2a: The user elects to create a profile.

    - Alternative Flow 2b: The user clicks on the link to retrieve a username.

    - Alternative Flow 2c: The user clicks on the link to reset a password.

    - Alternative Flow 2d: The user clicks on the Cancel button.

    - Alternative Flow 6a: The user's username is incorrect.  This scenario outlines the steps in the event that the user enters their username incorrectly.
	* 6a1: The user is presented with a pop-up window containing a message that the username is incorrect
	* 6a2: The user acknowledges the message by clicking on an OK button
	* 6a3: The Username and Password fields are cleared by the application
	* 6a4: The user enters the login information
	* 6a5: The user clicks on the OK button or uses the Enter key to have the application reassess their credentials
	* 6a6: The user is logged into the application
    - Termination Outcome: The user is taken to the main menu screen.

    - Alternative Flow 6b: The user's password is incorrect.  This scenario outlines the steps in the event that the user enters their password incorrectly once.
	* 6b1a: The user is presented with a pop-up window containing a message that the password is incorrect and they have two attempts remaining to enter it correctly before the password must be reset.
	* 6b1b: The user acknowledges the message by clicking on an OK button
	* 6b1c: The Username and Password fields are cleared by the application
	* 6b1d: The user enters the login information
	* 6b1e: The user clicks on the OK button or uses the Enter key to have the application reassess their credentials
	* 6b1f: The user is logged into the application
    - Termination Outcome: The user is taken to the main menu screen.

    - Alternative Flow 6b2: The user's password is entered incorrectly twice.
	* 6b2a: The user is presented with a pop-up window containing a message that the password is incorrect and they have one attempt remaining to enter it correctly before the password must be reset.
	* 6b2b: 

    - Alternative Flow 6b3: The user's password is entered incorrectly three times.

    - Alternative Flow 6c: The user leaves the Username field blank.

    - Alternative Flow 6d: The user leaves the Password field blank.

    

- [ ] **Main Menu State**
  - user shall be able to navigate between multiple screens via a menu screen
  - on the menu scren, the user shall be given the option to enter an in game state, study state, profile state, library state, and settings state

- [ ] **In-Game State**
  - user shall be presented with an unmastered deck from which they can draw - by clicking - new cards
  - upon drawing a new card, the card shall be displayed with information and an image of an unmastered character
  - if the mastery bar (*confirmed verbiage? - djs*) is filled by the nth consecutive successful attempt, the card shall be moved into the user's mastered deck
  - if the user fails to name the displayed card correctly, the mastery bar (*cf. prev. comment - djs*) is cleared, and the card reenters the unmastered deck without regard to which deck it was drawn from (unmastered v. mastered)

  - **In-Game Use Case:** User plays the game
    - Actor: User
    - Use Case Overview: The user decides to play the game.  The user is presented with an unmastered deck in the upper right of the game screen.  They draw a card by clicking on it.  The card moves to the center of the screen. The user *enters an answer using their keyboard or selects an answer from a word bank*.  The user's answer is correct/incorrect.  If correct, the mastery bar is incremented; if incorrect, the mastery bar is cleared and the card is returned to the unmastered deck.  If the mastery bar increment fills the mastery bar completely, the card is moved to the user's mastered deck.  The user continues playing by clicking on an unmastered card or makes a different selection from the menu.
    - Trigger: The user selected the option to play the game
    - Precondition1: The user has created a profile
    - Precondition2: The user has loaded a deck
    - Precondition3: There is at least one unmastered card in the deck
    - Basic Flow: This scenario outlines normal game state flow with at least one unmastered card in the unmastered deck
        * 1. The user chooses to play the game via the menu
        * 2. The user draws a card by clicking on the unmastered deck
        * 3. The user enters an answer
        * 4. The user's answer is correct
        * 5. The mastery bar is incremented
        * 6. The user selects another card
    - Termination Outcome: The deck is mastered

    - Alternative Flow 4a: The user's answer is incorrect.  This scenario outlines the steps in the event that the user does not correctly identify the card.
        * 4a1: The mastery bar is cleared
        * 4a2: The card is returned to the unmastered deck
    - Termination Outcome: The user proceeds by selecting a card from the unmastered deck

    - Alternative Flow 5a: After incrementing, the mastery bar is full.  This scenario outlines the steps in the event that the user has mastered an unmastered card.
        * 5a1: The card is moved to the mastered deck
    - Termination Outcome: The user proceeds by selecting a card from the unmastered deck
     
    - Alternative Flow 6a: There are no more unmastered cards in the unmastered deck.
        * 6a1: There are no more cards in the unmastered deck
        * 6a2: The application provides a summary report (?)
        * 6a3: The user is returned to the main menu
    - Termination Outcome: The user mastered the entire deck

    - Post condition: No cards remain in the unmastered deck

![alt text](https://github.com/rndmorris/Repertoire/blob/master/Game%20Use%20Case%20Diagram.png "In-Game State Use Case Diagram")

- [ ] **Study State**
  - user shall be able to browse through the cards in the deck that is currently loaded

  - **Study Use Case:** User decides to study cards
    - Actor: User
    - Use Case Overview: The user decides to study cards.  The user is presented with their combined unmastered and mastered deck in a grid-type view.  The user can stay in this state until they use the menu to change states.
    - Trigger: The user selected the option to study
    - Precondition1: The user has created a profile
    - Precondition2: The user has loaded a deck
    - Basic Flow: This scenario outlines normal study state flow
        * 1. The user chooses to study via the menu
        * 2. The user browses the cards in a grid-type view
        * 3. The user zooms in on a card by clicking on it
        * 4. The user returns to the grid-type view by clicking on the card a second time
    - Termination Outcome: The user decides to exit the study state by clicking on the menu

    - Post condition: The study state does not change.

- [ ] **Inventory State**
  - the user shall be able to browse their mastered cards by scrolling
  - the user shall be able to click on a card to examine it at a larger scale, in a similar manner to viewing drawn cards in the game state

  - **Inventory Use Case**
    - Actor: User
    - Inventory Use Case Overview: The user decides to peruse their mastered card inventory.  The user is presented with their cards in a "fan" from left to right.  They can scroll through the fanned cards.  The user can zoom in to focus on a specific card by clicking on it.  The user can return to the fanned card view by clicking on the card a second time.  The user can exit the inventory state using the menu.
    - Trigger: The user selected the option to examine their inventory of mastered cards
    - Precondition1: The user has created a profile
    - Precondition2: The user has loaded a deck
    - Precondition3: The user has mastered at least one card from the loaded deck
    - Basic Flow: This scenario outline the normal inventory flow
        * 1. The user chooses to examine their inventory via the menu
        * 2. The user is presented with their mastered cards fanned out from left to right
	* 3. The user can scroll through the cards (_mouse wheel? arrow key? - djs)
	* 4. The user can zoom in on a card by clicking on it
	* 5. The user returns to the fanned card view by clicking on the selected card again
	* 6. The user can scroll through the cards
        * 7. The user selects another card
    - Termination Outcome: The user decides to exit the inventory state by clicking on the menu.

    - Alternative Flow 1a: The user does not have a mastered card in the loaded deck.  This scenario outlines the steps in the event that they do not have a single mastered card in their inventory.
	* 1a1: The user chooses to examine their inventory via the menu
	* 1a2: The user is returned to the main menu
    - Termination Outcome: The inventory state requires at least one mastered card to enable the user to choose it.

    - Post condition: The inventory state does not change.

- [ ] **Profile State**
  - the user shall be able to view their settings and gameplay stats.
    - overall level of mastery (*experience? - djs*)
    - number of cards mastered
    - most daunting card
    - longest correct guess streak
    - *optional medal concept? - djs*

- [ ] **Library State**
  - the user shall be able to scroll through a variety of sets or decks (*distinction - djs?*)
  - upon clicking on a deck, deck details shall be displayed along with an option to select the deck

- [ ] **Settings State**
  - the user shall be presented with a user-friendly display of the game's settings including:
    - viewable font
    - in-game volume