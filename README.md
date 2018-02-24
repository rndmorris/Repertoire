# Repertoire
## A flashcard-esque learning tool

Work Assignments
- Aaron
  - Database
  - Web-API
- Dave
  - Project documentation
  - Target audience & use cases
  - Login Screen
- Tucker
  - General Java client
  - Design

Target Audience
- [ ] As a language learning support tool
  - [ ] With its pre-loaded libraries of language cards, Repertoire serves as an intuitive, enjoyable approach to reinforcement language instruction.  With a study mode - where the user can study virtual flash cards to help with memorizing non-phonetic ideograms - and a game mode - where the user can work on learning ideograms through a flash card game - users are empowered to choose how they learn a second, or subsequent, language.

- [ ] As a platform for general subject learning
  - [ ] With its customizable card deck creation, Repertoire can support any type of learning for a student of any subject.  Users can create decks of custom cards for study or play to help them reinforce recall of pertinent facts.  Here's a small set of potential deck sets
    - [ ] For the amateur lepidopterist: create a butterfly deck to reinforce your field identification skills
    - [ ] For the computer science student: create a decimal to hexadecimal base deck to reinforce your skills at different number bases
    - [ ] For the advanced math student: create a derivative rules deck to help memorize how to take those pesky derivatives depending on the expressions being evaluated
    - [ ] For the nursing student: create an anatomy deck to help commit assorted organs and bones to memory


Feature List
- [ ] The application's feature include:
    - [ ] a game mode to work on subject mastery
    - [ ] a study mode to review your subject at your pace
    - [ ] customizable dictionaries, you can study any subject, any time


Use Cases
- [ ] Main Menu State
  - [ ] user shall be able to navigate between multiple screens via a menu screen
  - [ ] On the menu scren, the user shall be given the option to enter an in game state, study state, profile state, library state, and settings state

- [ ] In-Game State
  - [ ] user shall be presented with an unmastered deck from which they can draw - by clicking - new cards
  - [ ] upon drawing a new card, the card shall be displayed with information and an image of an unmastered character
  - [ ] if the mastery bar (*confirmed verbiage? - djs*) is filled by the nth consecutive successful attempt, the card shall be moved into the user's mastered deck
  - [ ] if the user fails to name the displayed card correctly, the mastery bar (*cf. prev. comment - djs*) is cleared, and the card reenters the unmastered deck without regard to which deck it was drawn from (unmastered v. mastered)
  - In-Game Use Case: User plays the game
    - Actor: User
    - Use Case Overview: The user decides to play the game.  The user is presented with an unmastered deck in the upper right of the game screen.  They draw a card by clicking on it.  The card moves to the center of the screen. The user *enters an answer using their keyboard or selects an answer from a word bank*.  The user's answer is correct/incorrect.  If correct, the mastery bar is incremented; if incorrect, the mastery bar is cleared and the card is returned to the unmastered deck.  If the mastery bar increment fills the mastery bar completely, the card is moved to the user's mastered deck.  The user continues playing by clicking on an unmastered card or makes a different selection from the menu.
    - Trigger: The user selected the option to play the game
    - Precondition1: The user has created a profile
    - Precondition2: The user has loaded a deck
    - Precondition3: There is at least one unmastered card in the deck
    - Basic Flow: This scenario outlines normal game flow with at least one unmastered card in the unmastered deck
      - 1: The user chooses to play the game via the menu.
      - 2: The user draws a card by clicking on the unmastered deck.
      - 3: The user enters an answer.
      - 4: The user's answer is correct.
      - 5: The mastery bar is incremented.
      - 6: The user selects another card.
      - Termination Outcome: The deck is mastered.

    - Alternative Flow 4a: The user's answer is incorrect.  This scenario outlines the steps in the event that the user does not correctly identify the card.
      - 4a1: The mastery bar is cleared.
      - 4a2: The card is returned to the unmastered deck.
      - Termination Outcome: The user proceeds by selecting a card from the unmastered deck.

    - Alternative Flow 5a: After incrementing, the mastery bar is full.  This scenario outlines the steps in the event that the user has mastered an unmastered card.
      - 5a1: The card is moved to the mastered deck.
      - Termination Outcome: The user proceeds by selecting a card from the unmastered deck.
     
    - Alternative Flow 6a: There are no more unmastered cards in the unmastered deck.
      - 6a1: There are no more cards in the unmastered deck.
      - 6a2: The application provides a summary report. (?)
      - 6a3: The user is returned to the main menu.
      - Termination Outcome: The user mastered the entire deck.

    - Post condition: No cards remain in the unmastered deck.

![alt text](https://github.com/rndmorris/Repertoire/blob/master/Game%20Use%20Case%20Diagram.png "In-Game State Use Case Diagram")

- [ ] Study State
  - [ ] the user shall be able to browse through the cards in the deck that is currently loaded

- [ ] Inventory State
  - [ ] the user shall be able to browse their mastered cards by scrolling
  - [ ] the user shall be able to click on a card to examine it at a larger scale, in a similar manner to viewing drawn cards in the game state

- [ ] Profile State
  - [ ] the user shall be able to view their settings and gameplay stats.
    - [ ] overall level of mastery (*experience? - djs*)
    - [ ] number of cards mastered
    - [ ] most daunting card
    - [ ] longest correct guess streak

- [ ] Library State
  - [ ] the user shall be able to scroll through a variety of sets or decks (*distinction - djs?*)
  - [ ] upon clicking on a deck, deck details shall be displayed along with an option to select the deck

- [ ] Settings State
  - [ ] the user shall be presented with a user-friendly display of the game's settings including:
    - [ ] viewable font
    - [ ] in-game volume