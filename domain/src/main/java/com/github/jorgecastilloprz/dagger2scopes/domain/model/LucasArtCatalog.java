/*
 * Copyright (C) 2015 Jorge Castillo Pérez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jorgecastilloprz.dagger2scopes.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mock game catalog.
 *
 * @author Jorge Castillo Pérez
 */
public class LucasArtCatalog implements GameCatalog {

  private List<Game> games;

  public LucasArtCatalog() {
    initCatalog();
  }

  private void initCatalog() {

    games = new ArrayList<>();

    LucasArtGame lucasArtGame =
        new LucasArtGame("http://upload.wikimedia.org/wikipedia/en/5/5e/Maniac_Mansion_artwork.jpg",
            "Maniac Mansion", "1987",
            "Maniac Mansion is a 1987 graphic adventure game developed and published by Lucasfilm "
                + "Games. Initially released for the Commodore 64 and Apple II, it was Lucasfilm's"
                + " foray into video game publishing. The game follows teenager Dave Miller as he "
                + "ventures into a mansion and attempts to rescue his girlfriend from an evil mad "
                + "scientist, whose family has been controlled by a sentient meteor that crashed "
                + "near the mansion 20 years earlier. The player uses a point-and-click interface "
                + "to guide Dave and two of his friends through the mansion while avoiding its "
                + "dangerous inhabitants and solving puzzles.");

    games.add(lucasArtGame);

    lucasArtGame =
        new LucasArtGame("http://upload.wikimedia.org/wikipedia/en/1/17/Zak_McKracken_artwork.jpg",
            "Zak McKracken", "1988", "Zak McKracken and the Alien Mindbenders is a graphical "
            + "adventure game, originally released in October 1988,[1] published by Lucasfilm Games "
            + "(later known as LucasArts). It was the second game to use the SCUMM engine, after "
            + "Maniac Mansion. The project was led by David Fox, with Matthew Alan Kane as the "
            + "co-designer and co-programmer.\n"
            + "Like Maniac Mansion, it was developed for the Commodore 64 and later released in "
            + "1988 for that system and IBM PC (MS-DOS).[1] An Apple II version was apparently "
            + "planned, but never released. The following year in 1989, the game was ported to the "
            + "Amiga and Atari ST. An MS-DOS version with enhanced graphics was also released.");
    games.add(lucasArtGame);

    lucasArtGame = new LucasArtGame(
        "http://www.adventuresplanet.it/public/images/recensioni/INDY3_rece_img1.gif",
        "Indiana Jones and the Last Crusade", "1989", "Indiana Jones and the Last Crusade: The "
        + "Graphic Adventure is a graphic adventure game, originally released in 1989 (to "
        + "coincide with the release of the film of the same name), published by Lucasfilm "
        + "Games (now LucasArts). It was the third game to use the SCUMM engine.");
    games.add(lucasArtGame);

    lucasArtGame = new LucasArtGame(
        "http://cdn.akamai.steamstatic.com/steam/apps/32340/ss_3f3d477d6c671ffbd0fbc67ea0c03496d93320a9.1920x1080.jpg",
        "Loom", "1990", "Loom is a graphical adventure game originally released in 1990. It was "
        + "both developed and published by Lucasfilm Games (later called LucasArts) and was the "
        + "fourth game to use the SCUMM adventure game engine. The project was led by Brian "
        + "Moriarty, a former Infocom employee and author of the classic text adventures "
        + "Wishbringer (1985), Trinity (1986) and Beyond Zork (1987).\n"
        + "In 2009 the game was released for digital download on Steam content delivery system.");
    games.add(lucasArtGame);

    lucasArtGame =
        new LucasArtGame("http://www.rogerdavies.com/wp-content/uploads/2009/08/monkeyisland_1.jpg",
            "The Secret of Monkey Island", "1991", "The Secret of Monkey Island is a 1990 "
            + "point-and-click graphic adventure game developed and published by Lucasfilm Games. It "
            + "takes place in a fantastic version of the Caribbean during the age of piracy. The player "
            + "assumes the role of Guybrush Threepwood, a young man who dreams of becoming a pirate and "
            + "explores fictional islands while solving puzzles.");
    games.add(lucasArtGame);

    lucasArtGame = new LucasArtGame("http://www.juegomania.org/emuladores/pc/6/6210.jpg",
        "Monkey Island 2: LeChuck's Revenge", "1991", "Monkey Island 2: LeChuck's Revenge is an "
        + "adventure game developed and published by LucasArts in 1991. It was the second game "
        + "of the Monkey Island series, following The Secret of Monkey Island, and the sixth "
        + "LucasArts game to use the SCUMM engine. It was the first game to use the iMUSE sound "
        + "system.");
    games.add(lucasArtGame);

    lucasArtGame = new LucasArtGame("http://img.gamefaqs.net/screens/a/7/4/gfs_45322_2_19.jpg",
        "Indiana Jones and the Fate of Atlantis", "1992", "Indiana Jones and the Fate of Atlantis "
        + "is a point-and-click adventure game by LucasArts originally released in 1992. Almost a "
        + "year later, it was reissued on CD-ROM as an enhanced \"talkie\" edition with full voice "
        + "acting and digitized sound effects. In 2009, this version was also released as an "
        + "unlockable extra of the Wii action game Indiana Jones and the Staff of Kings, and as "
        + "a digitally distributed Steam title. The seventh game to use the script language SCUMM, "
        + "Fate of Atlantis has the player explore environments and interact with objects and "
        + "characters by using commands constructed with predetermined verbs. It features three "
        + "unique paths to select, influencing story development, gameplay and puzzles. The game "
        + "used an updated SCUMM engine and required a 286-based PC, although it still runs as a "
        + "real-mode DOS application. The CD talkie version required EMS memory enabled to load "
        + "the voice data.");
    games.add(lucasArtGame);

    lucasArtGame = new LucasArtGame(
        "http://oyster.ignimgs.com/wordpress/stg.ign.com/2014/06/Day-of-the-Tentacle-IGN-20-720x540.jpg",
        "Day of the Tentacle", "1993", "Day of the Tentacle, also known as Maniac Mansion II: "
        + "Day of the Tentacle,[2][3] is a 1993 graphic adventure game developed and published "
        + "by LucasArts. It is the sequel to the 1987 game Maniac Mansion. The game's plot follows "
        + "Bernard Bernoulli and his friends Hoagie and Laverne as they attempt to stop the evil "
        + "Purple Tentacle—a sentient, disembodied tentacle—from taking over the world. The player "
        + "takes control of the three and solves puzzles while using time travel to explore "
        + "different periods of history.");
    games.add(lucasArtGame);

    lucasArtGame =
        new LucasArtGame("http://wolf.flatrock.org.nz/images/Sam_and_Max.JPG", "Sam and Max",
            "1993", "Sam & Max Hit the Road is a graphic adventure video game released "
            + "by LucasArts during the company's adventure games era. The game was originally released "
            + "for MS-DOS in 1993 and for Mac OS in 1995. A 2002 re-release included compatibility with "
            + "Windows. The game is based on the comic characters of Sam and Max, the "
            + "\"Freelance Police\", an anthropomorphic dog and \"hyperkinetic rabbity thing\". "
            + "The characters, created by Steve Purcell, originally debuted in a 1987 comic book "
            + "series. Based on the 1989 Sam & Max comic On the Road, the duo take the case of a "
            + "missing bigfoot from a nearby carnival, traveling to many Americana tourist sites to "
            + "solve the mystery.");
    games.add(lucasArtGame);

    lucasArtGame = new LucasArtGame(
        "http://upload.wikimedia.org/wikipedia/fi/d/d7/Full_Throttle_kuvakaappaus.png",
        "Full Throttle", "1995",
        "Full Throttle is a computer adventure game developed and published by LucasArts. "
            + "It was designed by Tim Schafer, who would later go on to design Grim Fandango, "
            + "Psychonauts and Brütal Legend. The game features voice actors Roy Conrad and Mark "
            + "Hamill. It was released on April 30, 1995. It is the tenth game to use the SCUMM "
            + "adventure game engine.");
    games.add(lucasArtGame);

    lucasArtGame =
        new LucasArtGame("http://jogorama.com.br/arquivos/telas/3158/3158_13.jpg", "The Dig",
            "1995", "The Dig is a graphical point-and-click adventure game developed by LucasArts "
            + "and released in 1995 as a CD-ROM for PC and Macintosh computers. Like other "
            + "LucasArts adventure games, it uses the SCUMM engine, and features full voice-over "
            + "soundtrack including notable voice actors Robert Patrick and Steven Blum, and a "
            + "digital orchestral score. The game uses a combination of drawn two-dimensional "
            + "artwork and limited pre-rendered three-dimensional movies.");
    games.add(lucasArtGame);

    lucasArtGame = new LucasArtGame(
        "http://firsthour.net/screenshots/curse-of-monkey-island/curse-of-monkey-island-voodoo-priestess.jpg",
        "The Curse of Monkey Island", "1997",
        "The Curse of Monkey Island is an adventure game developed and published by "
            + "LucasArts, and the third game in the Monkey Island series. It was released in 1997 "
            + "and followed the successful games The Secret of Monkey Island and Monkey Island 2: "
            + "LeChuck's Revenge. The game is the twelfth and last LucasArts game to use the SCUMM "
            + "engine, which was extensively upgraded for its last outing before being replaced by "
            + "the GrimE engine for the next game in the series, Escape from Monkey Island. The "
            + "Curse of Monkey Island is the first Monkey Island game to include voice acting, and "
            + "has a more cartoon-ish graphic style than the earlier games.");
    games.add(lucasArtGame);

    lucasArtGame = new LucasArtGame(
        "http://www.wescoregames.com/dynimgs/games/pc-grim-fandango-reactivate/grim_fandango_reactivate_188945.jpg",
        "Grim Fandango", "1998",
        "Grim Fandango is a dark comedy neo-noir adventure game released by LucasArts "
            + "in 1998 for Windows, with Tim Schafer as the game's project leader. It is the first "
            + "adventure game by LucasArts to use 3D computer graphics overlaid on pre-rendered, "
            + "static backgrounds. As with other LucasArts adventure games, the player must "
            + "converse with other characters and examine, collect, and use objects correctly "
            + "to solve puzzles in order to progress.");
    games.add(lucasArtGame);

    lucasArtGame = new LucasArtGame("https://beforeitsoverau.files.wordpress.com/2014/08/167_4.jpg",
        "Escape from Monkey Island", "2000",
        "Escape from Monkey Island is a computer adventure game developed and released by LucasArts "
            + "in 2000. It is the fourth game in the Monkey Island series and the first one to use "
            + "3D graphics. The game centers on the pirate Guybrush Threepwood, who returns home "
            + "with his wife Elaine Marley after their honeymoon, to find her erroneously declared "
            + "dead, and her office of governor up for election. Guybrush must find a way to "
            + "restore Elaine to office, while uncovering a plot to turn the Caribbean into a "
            + "tourist trap, headed by his nemesis LeChuck and an Australian conspirator Ozzie "
            + "Mandrill."
            + "Escape was the last of LucasArts' adventure games to be released. It was also the "
            + "second and last game to use the GrimE engine, which was upgraded from its first use "
            + "in Grim Fandango.");
    games.add(lucasArtGame);
  }

  @Override public List<Game> getGames() {
    Collections.reverse(games);
    return games;
  }
}
