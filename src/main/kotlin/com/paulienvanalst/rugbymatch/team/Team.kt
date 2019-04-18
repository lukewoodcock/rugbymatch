package com.paulienvanalst.rugbymatch.team


data class Team (val players: List<Player>, val name: TeamName) {
    /**
     * A team has enough players when it has at least 15 players
     */
    val hasEnoughPlayers : Boolean
        get() = players.count() > 14

    /**
     * A team has substitutes when it has more than 15 players
     */
    val hasAnySubstitutes : Boolean
        get() = players.any { !it.isStarting }

    /**
     * A team has enough starting players when there are at least 15 players
     * wearing back numbers 1 until 15
     */
    val hasEnoughStartingPlayers : Boolean
        get() = players.count { it.isStarting } > 14

    /**
     * Finds the scrum half of a team and returns null if none exist
     */
    val scrumHalf : Player? = players.find { it.position == Position.SCRUM_HALF }

    /**
     * The captain, when present, should always where back number 7
     * #note for the purposes of these exercises, a scrum-half wears
     * a number 7 instead of 9 - see the Position enums
     */
    fun captainBackNumber(): Int? {
        return this.scrumHalf!!.backNumber
    }

    /**
     * When no scrumhalf present (player with back number 7)
     * the captain is wearing the first back number we can find among the starting players.
     */
    fun replacingCaptainBackNumber(): Int? {
        return this.scrumHalf?.backNumber ?: players.first { it.isStarting }.backNumber
    }


}

