import console.LoginAndRegisterUI;
import console.MainMenuUI;

import java.io.Serializable;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConferenceSystem implements Serializable {
    Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void run() {
        // Setup logger
        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);
        LOGGER.addHandler(handlerObj);
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);

        /*
         * User flow
         *
         * ** Make attendee operations executable by admins too
         * ** So it'd be check if organizer OR (if target == self AND is attendee)
         *
         * /
         * |-Login or Register                                           ** Ez clap menu thing
         *   |- Go to messaging
         *   |  |-Look at the list of messages                           ** Ez clap menu thing
         *   |  | |-Select a conversation                                ** Custom message boi
         *   |  |   |-Send message
         *   |  |   |-Read message
         *   |  |   |-See users in conversation
         *   |  |-Start a new message with someone on your contact list
         *   |
         *   |- Go to contacts                                            ** Ez clap menu thing
         *   |  |-View contacts                                           ** Custom stuff
         *   |  |-Request someone to connect
         *   |  |-View people who want to slide into your DMs
         *   |
         *   |- Go to conferences                                         ** Ez clap menu thing
         *  |-> Create a conference                                   ** Form boi
         *  |-> Join a conference                                     ** Ez clap menu thing
         *  |   |-> Find a conference from a list and join it
         *  |-> View a joined conference                              ** Ez clap menu thing
         *      |-> View the general conference details (start, end, name, etc.)
         *      |
         *      |-> View the event calendar
         *      |
         *      |-> Event stuff
         *      |   |-> View list of events (Attendee)
         *      |   |-> View list of events (Speaker)
         *      |   |-> View event room
         *      |   |-> View event time stuff
         *      |   |-> Register for event
         *      |   |-> Unregister from event
         *      |   |-> Make a conversation for everyone in this event (Speaker)
         *      |   |-> View a list of attendees, speakers
         *      |   |-> Organizer related operations
         *      |       |-> Edit the name, dates, etc.
         *      |       |-> Create event
         *      |       |-> Delete event
         *      |
         *      |-> Room stuff
         *      |   |-> View calendar
         *      |   |-> Organizer related operations
         *      |        |-> Edit the room capacity, location, etc.
         *      |        |-> Create room
         *      |        |-> Delete room
         *      |
         *      |-> Conference management stuff
         *          |-> Delete the conference
         *          |-> Slide into the DM of any attendee
         *          |-> Add/Remove organizer
         * */

        /**
         * UI Components to build
         * - n-column table with numbered rows
         * - messaging thing
         * - Form component
         */

        LoginAndRegisterUI loginAndRegisterUI = new LoginAndRegisterUI(userController);
        MainMenuUI mainMenuUI = new MainMenuUI();

        while (true) {
            if (userController.getCurrentUser() == null) {
                loginAndRegisterUI.run();
            } else {
                mainMenuUI.run();
            }
        }
    }
}
