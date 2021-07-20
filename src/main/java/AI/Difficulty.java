package AI;

public enum Difficulty {
    // DIFFICULTY (CHANCE OF MAKING RANDOM MOVE, HOW FAR AHEAD IT IS ALLOWED TO LOOK)
    EASY (0.5, 2, true),
    MEDIUM (0.3, 3, true),
    HARD (0.1, 4, true),
    EXPERT (0, Integer.MAX_VALUE, false),
    CUSTOM(0, 0, false);

    private double randomMoveChance;
    private int lookAheadLimit;
    private boolean randomMoveChanceHasBeenChanged;
    private boolean lookAheadLimitHasBeenChanged;
    private boolean randomFirstMove;
    private boolean randomFirstMoveHasBeenChanged;

    Difficulty (double randomMoveChance, int lookAheadLimit, boolean randomFirstMove) {
        this.randomMoveChance = randomMoveChance;
        this.lookAheadLimit = lookAheadLimit;
        this.randomFirstMove = randomFirstMove;
        this.randomMoveChanceHasBeenChanged = false;
        this.lookAheadLimitHasBeenChanged = false;
        this.randomFirstMoveHasBeenChanged = false;
    }

    public double getRandomMoveChance() throws IllegalAccessException {
        if (this == CUSTOM) {
            if (!this.randomMoveChanceHasBeenChanged) {
                throw new IllegalAccessException("You are not allowed to read the values of a custom difficulty without initializing it");
            }
        }
        return this.randomMoveChance;
    }

    public int getLookAheadLimit() throws IllegalAccessException{
        if (this == CUSTOM) {
            if (!this.lookAheadLimitHasBeenChanged) {
                throw new IllegalAccessException("You are not allowed to read the values of a custom difficulty without initializing it");
            }
        }
        return this.lookAheadLimit;
    }

    public boolean getRandomFirstMove() throws IllegalAccessException{
        if (this == CUSTOM) {
            if (!this.randomFirstMoveHasBeenChanged) {
                throw new IllegalAccessException("You are not allowed to read the values of a custom difficulty without initializing it");
            }
        }
        return this.randomFirstMove;
    }

    public void setRandomMoveChange(double randomMoveChance) throws IllegalAccessException, IllegalArgumentException {
        if (this == CUSTOM) {
            if (this.randomMoveChanceHasBeenChanged) {
                throw new IllegalAccessException("You are not allowed to change custom difficulties more than once");
            }
            if (randomMoveChance < 0) {
                throw new IllegalArgumentException("Random move chance must be greater or equal to zero");
            }
            if (randomMoveChance > 1) {
                throw new IllegalArgumentException("Random move chance must be less than or equal to one");
            }
            this.randomMoveChance = randomMoveChance;
            this.randomMoveChanceHasBeenChanged = true;
        } else {
            throw new IllegalAccessException("Changing the value of non custom difficulties is not allowed");
        }
    }

    public void setLookAheadLimit(int lookAheadLimit) throws IllegalAccessException, IllegalArgumentException {
        if (this == CUSTOM) {
            if (this.lookAheadLimitHasBeenChanged) {
                throw new IllegalAccessException("You are not allowed to change custom difficulties more than once");
            }
            if (lookAheadLimit < 0) {
                throw new IllegalArgumentException("Look ahead limit must be greater than 0");
            }
            this.lookAheadLimit = lookAheadLimit;
            this.lookAheadLimitHasBeenChanged = true;
        } else {
            throw new IllegalAccessException("Changing the value of non custom difficulties is not allowed");
        }

    }

    public void setRandomFirstMove(boolean randomFirstMove) throws IllegalAccessException, IllegalArgumentException {
        if (this == CUSTOM) {
            if (this.randomFirstMoveHasBeenChanged) {
                throw new IllegalAccessException("You are not allowed to change custom difficulties more than once");
            }
            this.randomFirstMove = randomFirstMove;
            this.randomFirstMoveHasBeenChanged = true;
        } else {
            throw new IllegalAccessException("Changing the value of non custom difficulties is not allowed");
        }
    }


    public String getAllProperties() {
        String output = "";
        output += "Type of difficulty is: " + this + "\n";
        try {
            output += "Random move chance of difficulty is " + this.getRandomMoveChance() + "\n";
        } catch (IllegalAccessException exception) {
            output += "Custom difficulty has not been initialized\n";
            return output;
        }
        try {
            output += "Look ahead limit of difficulty is " + this.getLookAheadLimit() + "\n";
        } catch (IllegalAccessException exception) {
            output += "Custom difficulty has not been initialized\n";
            return output;
        }
        try {
            output += "Random first move is " + Difficulty.randomFirstMoveToString(this.getRandomFirstMove()) + "\n";
        } catch (IllegalAccessException exception) {
            output += "Custom difficulty has not been initialized\n";
            return output;
        }
        return output;
    }


    public static String randomFirstMoveToString(boolean randomFirstMove) {
        if (randomFirstMove) return "on";
        return "off";
    }


    @Override
    public String toString() {
        if (this == EASY) {
            return "Easy";
        }
        if (this == MEDIUM) {
            return "Medium";
        }
        if (this == HARD) {
            return "Hard";
        }
        if (this == EXPERT) {
            return "Expert";
        }
        if (this == CUSTOM) {
            return "Custom";
        }
        return "null";
    }


}

