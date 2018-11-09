![App Logo](https://i.imgur.com/YEtbVO1.png)

# ChessTime

Android app that emulates a two player chess clock.

## Description

Player selects an amount of time with top seekbar, then creates timer with bottom button.

Player taking first turn presses their own button, and again at the end of their turn.

After first turn, each player presses their own button to pause their clock and resume their opponent's.

Players are only able to end turn after at least 1 second has passed (shown by a green turn indicator).

Short press center timer button to pause/resume.

Long press center timer button to reset.

## Getting Started

### Dependencies

* Requires Android 5.0.1 or newer

### Installing

* APKs can be found in [ChessTime/app/release/](https://github.com/DallasGianniny/ChessTime/tree/master/app/release)
* [v1.0.0](https://github.com/DallasGianniny/ChessTime/raw/master/app/release/ChessTime_1.0.0.apk)


### Executing program

* Download chosen APK file to device and install

![Starting Screen](https://i.imgur.com/DB0YQVK.png)
![Starting Screen Landscape](https://i.imgur.com/EkfhgbV.png)
![Low End Selection](https://i.imgur.com/N2IX08Y.png)
![High End Selection](https://i.imgur.com/xl2DCOL.png)
![Main Timer](https://i.imgur.com/WPNkCUt.png)
![Main Timer Started](https://i.imgur.com/P1Zoy7l.png)

(Images simulated on a Nexus X5)

## Author

Dallas Gianniny  

## Version History
* 1.0.0
    * Changed time selection from discrete to continuous
    * Added pause indicator
* 0.9.4
    * Turn button colors now adaptive
    * Integrated first turn buttons into main buttons
    * Added turn indicator
    * Added one second minimum turn lockout and indicator
    * Multiple fixed bugs
* 0.9.3
    * Added app icon
    * Minor bug fixes
* 0.9.2
    * Changed UI to better conform across different screen sizes
* 0.9.0
    * Added main menu to allow user set time
    * Added landscape activity view
    * Refreshed UI
* 0.1.0
    * Initial Release
    * Hardcoded timer
    * Multiple known bugs
