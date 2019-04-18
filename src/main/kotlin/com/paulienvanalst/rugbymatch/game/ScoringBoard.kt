package com.paulienvanalst.rugbymatch.game

import com.paulienvanalst.rugbymatch.events.ScoringEvent
import com.paulienvanalst.rugbymatch.events.StartGame
import com.paulienvanalst.rugbymatch.team.NotImplementedException
import com.paulienvanalst.rugbymatch.team.TeamName
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
open class ScoringBoard {
    private lateinit var hostingTeam : TeamName
    private lateinit var visitingTeam : TeamName

    private var scoringHistory : List<Score> = emptyList()

    @EventListener
    fun initialize(start: StartGame) {
        hostingTeam = start.hostingTeam
        visitingTeam = start.visitingTeam
    }

    fun onScoringEvent(scoreEvent: ScoringEvent) {
        scoringHistory += Score(scoreEvent.team, scoreEvent.type)
    }

    fun currentScore(): GameScore = scoringHistory.getGameScore(hostingTeam, visitingTeam)

    fun clear () {
        scoringHistory = emptyList()

    }
}


