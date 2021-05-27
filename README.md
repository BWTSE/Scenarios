# Scenarios

[![DOI](https://zenodo.org/badge/313999830.svg)](https://zenodo.org/badge/latestdoi/313999830)

This repository contains the scenarios used for the experiment in the thesis [*The Broken Windows Theory in Software Engineering: An experiment on technical debt propagation*](https://doi.org/10.5281/zenodo.4812956) conducted by [Hampus Broman](https://github.com/HBroman) and [William Levén](https://github.com/williamleven) in 2021.

Read the thesis for more information about how the scenarios were used.

## Repository content

* `README.md` contains some general information about this repository.
* `.manual_rules.json` contains information about which SonarQube rules were triggered by the submissions and if the rule should be ignored or not.
* `[scenario name]` Each directory represents one of the scenarios used.
    * `high_debt/[scenario name]` contains the high debt version of the scenario.
        `*.java` the files constituting the scenario.
    * `low_debt/[scenario name]` contains the low debt version of the scenario.
        `*.java` the files constituting the scenario.
    * `instructions.md` the instructions accompanying the scenario in markdown format.
    * `*.Java` an example solution to the scenario.

## Versions
Tags denotes different versions of the scenarios used in the study and prestudy.

## Notes
1. If you want to run the scenario with the solution, copy the solution file(s) to the folder containing the rest of the scenario files.

If you have any questions or feel that anything is amiss, please [open an issue](https://github.com/BWTSE/Scenarios/issues).
