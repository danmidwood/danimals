# Danimals

In my final year of university I was committed to a "final project", a
free style do-what-ever-you-want sort of software thing.

As a pseudo/wannabe-intellectual, I'd been reading various things around
Chaos theory, biology, evolution, artificial intelligence and game
theory. Along with good and bad sci-fi about future humans, generally
the upload-your-brain-into-a-computer-and-live-forever type of thing.

This project became my ambitious plan to create a colony of AIs that
would hopefully do, well, something, anything really.
The something became a game theory problem known as "The Prisoner's
 Dilemma" and the AI of choice became Evolutionary Algorithms.

It had already been done by other people, but I didn't know that then, I
blame Google for not existing and myself for not really doing any
research at all.

I did know a little bit about people researching Artificial Intelligence
and that they use LISP, so I thought I should do that. I went to the
Library and found a LISP book, then I went to the internet and found a
lot of different LISPs. I tried to find out which was the best, but they
were all the best, so that meant none of them were the best. So I used
Java instead.

While I'd learnt lots of interesting and useful things at university
like how to build a linked list, I didn't really know how to do anything
actually useful. Apart from an old application I made as bored college
student to take over the keyboards and open the cd drives on friends'
computers, I think this was my first application.

So, prepare yourself, here is a litany of things with the adjective bad.


## Usage

Get the source code with this command:

    git clone git@github.com:danmidwood/danimals.git

The compile the files with:

    gradle build -x test

This will produce a bunch of warnings, just ignore them and pretend that
you never saw them, it's all completely fine™.

There are some tests included in the project but a couple of them fail,
printing scary errors to the screen and overall being terrifying. By
appending the `-x test` part to the command above, we can build the
application without running the tests, enabling us to remain in our
cloudy bubble of ignorance where everything is fine. I recommend it.

The source code will be built and sprayed around the build/ folder,
ignore it all and proceed to run the application with:

    java -jar build/libs/danimals.jar

This will popup a window like so,

![On first open](https://github.com/danmidwood/danimals/raw/graduation/docs/initial_open.png)

This is also completely normal, just drag the corner to make it bigger,
do so until it looks something like this:

![The first screen](https://github.com/danmidwood/danimals/raw/graduation/docs/home_screen.png)

These little guys, these are the Danimals:

![The Danimals](https://github.com/danmidwood/danimals/raw/graduation/docs/the_danimals.png)

Ignore the table below them, we'll get onto that later.

The column on the right is where a game can be defined. I wrote this
application to play the prisoner's dilemma but, also being an aspiring
programmer, I designed it for maximum extensability and configurability.

What that means is that you can create any possible game that you like
in this application, as long as it is a two player game, played in
rounds where each player chooses one of two fixed options, points are
rewarded based on the choices of each player and generally that the
rules to follow very closely match the prisoner's dilemma problem.

Fill in the boxes like this and hit the [Create Game] button.

![Creating a game](https://github.com/danmidwood/danimals/raw/graduation/docs/game_create.png)

After pressing the button a [Define Game] button will appear, let's
click that too.

A box like this will pop up,

![Game edit thin box](https://github.com/danmidwood/danimals/raw/graduation/docs/game_editing_thin.png)

expand it so that it doesn't look terrible

This is where the scores awared for each outcome can be edited, I don't
exactly remember what scores I used back in 2005 but they should be
entered so that.

* If one player cooperates and one player defects then the defector
  receives a large payoff and the cooperator gets nothing.

* If both players defect then they both receive a small payoff

* If both players cooperate then they both receive a medium payoff

Values like these fit the definition nicely:

![Game definition complete](https://github.com/danmidwood/danimals/raw/graduation/docs/game_edited.png)

Hit confirm and the game is created.

You'll see that the table at the bottom of the window is now partially
populated, but we can still continue to ignore it for now

![Partially populated table](https://github.com/danmidwood/danimals/raw/graduation/docs/partially_populated_table.png)

At the top of the column on the right, open the dropdown menu where
"Game" is written and select "Environment"

![Select the environment in the dropdown](https://github.com/danmidwood/danimals/raw/graduation/docs/selecting_environment.png)

In there you will have the opportunity to edit the bit string length of
each Danimal. This is the equivalent of a genome I think, but for
computer animals instead of real world animals like us (and plants too,
don't forget). A longer string allows more rules and complexity and
whatnot, so let's just leave that at its default setting of 64.

The Mutate Rate is a probability of mutation is each specimin of the
next generation. I don't remember if it's chance of mutation for the
whole string of per each bit, but I suspect the latter. Leave this at
the default too

![Environment settings](https://github.com/danmidwood/danimals/raw/graduation/docs/environment_settings.png)

Next, take a look at the fight selection and reproduction selection
sections from the dropdown, these determine how two Danimals are
selected to compete in the game (fight), and then again to produce the
following generation (reproduction).

![Fight Selection](https://github.com/danmidwood/danimals/raw/graduation/docs/fight_selection.png)

![Reproduction Selection](https://github.com/danmidwood/danimals/raw/graduation/docs/reproduction_selection.png)

I designed this with a pretty cool type of plugin architecture so that
a user can design their own algorithms for this. That additional
complexity is why the drop down boxes in these sections are all empty.

This is a problem. Since a strategy needs to be in place before we can
place the Danimals in competition, we can't even start the game now.

Let's move on.

Select the Parsing Rules section and you'll see something like this,

![Reproduction Selection](https://github.com/danmidwood/danimals/raw/graduation/docs/parsing_rules.png)

It's here where selections of the string can be assigned to either of
cooperate or defect. I can't remember whether it's zero indexed or not,
so let's add 1-30 to cooperate, then switch to defect and add 31-60
there.

![Cooperate rules](https://github.com/danmidwood/danimals/raw/graduation/docs/cooperate_rules.png)

![Defect rules](https://github.com/danmidwood/danimals/raw/graduation/docs/defect_rules.png)

It's possible by assign more than one section to a choice here, and I
believe there was at one point in the past some way to add more advanced
rules in this section (in particular, looking at what the opponent was
doing). I guess that those are lost something in the past.

Or, it's a gui problem. I restarted and made the window a bit bigger and
then an edit button appeared. This button allows rules to be toggled
based on how the opponent has played in their previous turns.

Let's ignore it for now anyway.

Just hit start and see what happens...

... Nothing that should really happen.

Because I built a strong and resilient application, it keeps on running
ever when there are errors and to keep on running is non-sensical.

![Running on nonsense](https://github.com/danmidwood/danimals/raw/graduation/docs/running_nonsense.png)

Selecting a Danimal in the grid will display their string, and also the
score that it measures out as. Colours (RGB) can be assigned based to
each section, painting the population in specific ways to highlight
their playing styles.
