package use_case.MainScene;

    /**
     * shows the input data for the main menu interactor.
     * encapsulates the action triggered by a specific button click.
     */
    public class MainInputData {
        private final String buttonName; // The name of the button clicked
        private final String filePath;  // Optional file path for file-related actions
        private boolean startClicked;
        private boolean historyClicked;
        private boolean battleClicked;

        /**
         * constructor for MainInputData.
         *
         * @param buttonName The name of the button clicked.
         * @param filePath   The file path associated with the action.
         */
        public MainInputData(String buttonName, String filePath) {
            this.buttonName = buttonName;
            this.filePath = filePath;
        }

        /**
         * constructor for button-only actions.
         *
         * @param buttonName The name of the button clicked.
         */
        public MainInputData(String buttonName) {
            this(buttonName, null);
        }

        /**
         * gets the name of the button clicked.
         *
         * @return The button name.
         */
        public String getButtonName() {
            return buttonName;
        }

        /**
         * gets the file path associated with the action.
         *
         * @return The file path, or null if not applicable.
         */
        public String getFilePath() {
            return filePath;
        }

        public boolean getStartClicked() { return startClicked; }
        public void setStartClicked(boolean startClicked) { this.startClicked = startClicked; }

        public boolean getHistorylicked() { return historyClicked; }
        public void setHistoryClicked(boolean historylicked) { this.historyClicked = historylicked; }

        public boolean getBattleClicked() { return battleClicked; }
        public void setBattleClicked(boolean battleClicked) { this.battleClicked = battleClicked; }

    }
