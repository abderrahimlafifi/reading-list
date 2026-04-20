package com.readinglist.app

object BookData {

    val books2018 = listOf(
        Section(
            "Fiction", listOf(
                Book("Tinker Tailor Soldier Spy", "John Le Carré", BookStatus.COMPLETE),
                Book("The Lathe of Heaven", "Ursula Le Guin", BookStatus.COMPLETE),
                Book("The Three-Body Problem", "Cixin Liu", BookStatus.COMPLETE),
                Book("The Dark Forest", "Cixin Liu", BookStatus.COMPLETE),
                Book("Death's End", "Cixin Liu", BookStatus.COMPLETE),
                Book("The Fifth Season", "N.K. Jemisin", BookStatus.COMPLETE),
                Book("The Obelisk Gate", "N.K. Jemisin", BookStatus.COMPLETE),
                Book("The Stone Sky", "N.K. Jemisin", BookStatus.COMPLETE),
                Book("Ancillary Justice", "Ann Leckie", BookStatus.COMPLETE),
                Book("Ancillary Sword", "Ann Leckie", BookStatus.COMPLETE),
                Book("Ancillary Mercy", "Ann Leckie", BookStatus.COMPLETE),
                Book("Behemoth: B-Max", "Peter Watts", BookStatus.COMPLETE),
                Book("Behemoth: Seppuku", "Peter Watts", BookStatus.COMPLETE),
                Book("Mr. Penumbra's 24-Hour Bookstore", "Robin Sloan", BookStatus.COMPLETE),
            )
        ),
        Section(
            "Nonfiction", listOf(
                Book("War on Peace", "Ronan Farrow", BookStatus.COMPLETE),
                Book("Fire and Fury", "Michael Wolff", BookStatus.COMPLETE),
                Book("Kitchen Confidential", "Anthony Bourdain", BookStatus.COMPLETE),
            )
        ),
        Section(
            "Academic", listOf(
                Book("The Structure of Scientific Revolutions", "Thomas Kuhn", BookStatus.COMPLETE),
                Book("Down Girl", "Kate Manne", BookStatus.COMPLETE),
                Book("The Book of Why", "Judea Pearl & Dana Mackenzie", BookStatus.COMPLETE),
            )
        ),
        Section(
            "Technical", listOf(
                Book("Machine Learning", "Tom Mitchell", BookStatus.IN_PROGRESS),
            )
        )
    )

    val books2019 = listOf(
        Section(
            "Fiction", listOf(
                Book("Binti", "Nnedi Okorafor", BookStatus.COMPLETE),
                Book("Binti: Home", "Nnedi Okorafor", BookStatus.COMPLETE),
                Book("Binti: Night Masquerade", "Nnedi Okorafor", BookStatus.COMPLETE),
                Book("The Freeze-Frame Revolution", "Peter Watts", BookStatus.COMPLETE),
                Book("Permutation City", "Greg Egan", BookStatus.COMPLETE),
                Book("Axiomatic", "Greg Egan", BookStatus.COMPLETE),
                Book("The Colonel", "Peter Watts", BookStatus.COMPLETE),
                Book("Cryptonomicon", "Neal Stephenson", BookStatus.IN_PROGRESS),
            )
        ),
        Section(
            "Nonfiction", listOf(
                Book("Eating Animals", "Jonathan Safran Foer", BookStatus.COMPLETE),
                Book("Being Mortal", "Atul Gawande", BookStatus.COMPLETE),
                Book("Complications", "Atul Gawande", BookStatus.COMPLETE),
                Book("Better", "Atul Gawande", BookStatus.COMPLETE),
            )
        ),
        Section(
            "Academic", listOf(
                Book("Sapiens", "Yuval Harari", BookStatus.COMPLETE),
                Book("Cybernetics", "Norbert Wiener", BookStatus.IN_PROGRESS),
                Book("The Algebraic Mind", "Gary Marcus", BookStatus.COMPLETE),
            )
        ),
        Section(
            "Technical", listOf(
                Book("Causal Inference in Statistics: A Primer", "Judea Pearl et al.", BookStatus.IN_PROGRESS),
                Book("Causal Inference", "Miguel Hernan & Jamie Robins", BookStatus.IN_PROGRESS),
                Book("Probability Theory: The Logic of Science", "E.T. Jaynes", BookStatus.IN_PROGRESS),
            )
        )
    )

    val booksQueue = listOf(
        Section(
            "Fiction", listOf(
                Book("The Death of Ivan Ilyich", "Leo Tolstoy"),
                Book("CivilWarLand in Bad Decline", "George Saunders"),
                Book("The Trial", "Franz Kafka"),
                Book("Sonora", "Hannah Lillith Assadi"),
                Book("Beyond the Rift", "Peter Watts"),
                Book("Cryptonomicon", "Neal Stephenson"),
            )
        ),
        Section(
            "Nonfiction", listOf(
                Book("Animal Liberation", "Peter Singer"),
                Book("Homo Deus", "Yuval Harari"),
                Book("The Checklist Manifesto", "Atul Gawande"),
                Book("When Breath Becomes Air", "Paul Kalanithi"),
                Book("Snowball in a Blizzard", "Steven Hatch"),
                Book("How to Change Your Mind", "Michael Pollan"),
                Book("Twitter and Tear Gas", "Zeynep Tufekci"),
            )
        ),
        Section(
            "Academic", listOf(
                Book("The Logic of Scientific Discovery", "Karl Popper"),
                Book("The Age of Surveillance Capitalism", "Shoshana Zuboff"),
                Book("Thinking Fast and Slow", "Daniel Kahneman"),
                Book("The Color of Law", "Richard Rothstein"),
                Book("Seeing Like a State", "James C. Scott"),
                Book("Against Method", "Paul Feyerabend"),
            )
        ),
        Section(
            "Technical", listOf(
                Book("Causality", "Judea Pearl"),
                Book("Bandit Algorithms", "Tor & Szepesvari"),
                Book("Reinforcement Learning and Optimal Control", "Dimitri Bertsekas"),
                Book("Information Theory, Inference, and Learning Algorithms", "David MacKay"),
                Book("The Principia", "Sir Isaac Newton"),
            )
        )
    )
}
