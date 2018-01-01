$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/Multiplication.feature");
formatter.feature({
  "line": 1,
  "name": "Users are able to send their multiplication",
  "description": "attempts, which may be correct or not. When users\nsend a correct attempt, they get a response indicating\nthat the result is the right one. Also, they get points\nand potentially some badges when they are right, so they\nget motivation to come back and keep playing. Badges are\nwon for the first right attempt and when the user gets 100,\n500 and 999 points respectively. If users send a wrong\nattempt, they don\u0027t get any point or badge.",
  "id": "users-are-able-to-send-their-multiplication",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 11,
  "name": "The user sends a first right attempt and gets a badge",
  "description": "",
  "id": "users-are-able-to-send-their-multiplication;the-user-sends-a-first-right-attempt-and-gets-a-badge",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 12,
  "name": "the user john_snow sends 1 right attempts",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "the user gets a response indicating the attempt is right",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "the user gets 10 points for the attempt",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "the user gets the FIRST_WON badge",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "line": 17,
  "name": "The user sends a second right attempt and gets points only",
  "description": "",
  "id": "users-are-able-to-send-their-multiplication;the-user-sends-a-second-right-attempt-and-gets-points-only",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 18,
  "name": "the user john_snow sends 1 right attempts",
  "keyword": "Given "
});
formatter.step({
  "line": 19,
  "name": "the user gets the FIRST_WON badge",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "the user john_snow sends 1 right attempts",
  "keyword": "When "
});
formatter.step({
  "line": 21,
  "name": "the user gets a response indicating the attempt is right",
  "keyword": "Then "
});
formatter.step({
  "line": 22,
  "name": "the user gets 10 points for the attempt",
  "keyword": "And "
});
formatter.step({
  "line": 23,
  "name": "the user does not get any badge",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "line": 25,
  "name": "The user sends a wrong attempt and gets nothing",
  "description": "",
  "id": "users-are-able-to-send-their-multiplication;the-user-sends-a-wrong-attempt-and-gets-nothing",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 26,
  "name": "the user john_snow sends 1 wrong attempts",
  "keyword": "When "
});
formatter.step({
  "line": 27,
  "name": "the user gets a response indicating the attempt is wrong",
  "keyword": "Then "
});
formatter.step({
  "line": 28,
  "name": "the user gets 0 points for the attempt",
  "keyword": "And "
});
formatter.step({
  "line": 29,
  "name": "the user does not get any badge",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenarioOutline({
  "comments": [
    {
      "line": 31,
      "value": "# Checks the Bronze, Silver and Gold badges"
    }
  ],
  "line": 32,
  "name": "The user sends a right attempt after \u003cprevious_attempts\u003e right attempts and then gets a badge \u003cbadge_name\u003e",
  "description": "",
  "id": "users-are-able-to-send-their-multiplication;the-user-sends-a-right-attempt-after-\u003cprevious-attempts\u003e-right-attempts-and-then-gets-a-badge-\u003cbadge-name\u003e",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 33,
  "name": "the user john_snow sends \u003cprevious_attempts\u003e right attempts",
  "keyword": "Given "
});
formatter.step({
  "line": 34,
  "name": "the user john_snow sends 1 right attempts",
  "keyword": "When "
});
formatter.step({
  "line": 35,
  "name": "the user gets a response indicating the attempt is right",
  "keyword": "Then "
});
formatter.step({
  "line": 36,
  "name": "the user gets 10 points for the attempt",
  "keyword": "And "
});
formatter.step({
  "line": 37,
  "name": "the user gets the \u003cbadge_name\u003e badge",
  "keyword": "And "
});
formatter.examples({
  "line": 39,
  "name": "",
  "description": "",
  "id": "users-are-able-to-send-their-multiplication;the-user-sends-a-right-attempt-after-\u003cprevious-attempts\u003e-right-attempts-and-then-gets-a-badge-\u003cbadge-name\u003e;",
  "rows": [
    {
      "cells": [
        "previous_attempts",
        "badge_name"
      ],
      "line": 40,
      "id": "users-are-able-to-send-their-multiplication;the-user-sends-a-right-attempt-after-\u003cprevious-attempts\u003e-right-attempts-and-then-gets-a-badge-\u003cbadge-name\u003e;;1"
    },
    {
      "cells": [
        "9",
        "BRONZE_MULTIPLICATOR"
      ],
      "line": 41,
      "id": "users-are-able-to-send-their-multiplication;the-user-sends-a-right-attempt-after-\u003cprevious-attempts\u003e-right-attempts-and-then-gets-a-badge-\u003cbadge-name\u003e;;2"
    },
    {
      "cells": [
        "49",
        "SILVER_MULTIPLICATOR"
      ],
      "line": 42,
      "id": "users-are-able-to-send-their-multiplication;the-user-sends-a-right-attempt-after-\u003cprevious-attempts\u003e-right-attempts-and-then-gets-a-badge-\u003cbadge-name\u003e;;3"
    },
    {
      "cells": [
        "99",
        "GOLD_MULTIPLICATOR"
      ],
      "line": 43,
      "id": "users-are-able-to-send-their-multiplication;the-user-sends-a-right-attempt-after-\u003cprevious-attempts\u003e-right-attempts-and-then-gets-a-badge-\u003cbadge-name\u003e;;4"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 41,
  "name": "The user sends a right attempt after 9 right attempts and then gets a badge BRONZE_MULTIPLICATOR",
  "description": "",
  "id": "users-are-able-to-send-their-multiplication;the-user-sends-a-right-attempt-after-\u003cprevious-attempts\u003e-right-attempts-and-then-gets-a-badge-\u003cbadge-name\u003e;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 33,
  "name": "the user john_snow sends 9 right attempts",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 34,
  "name": "the user john_snow sends 1 right attempts",
  "keyword": "When "
});
formatter.step({
  "line": 35,
  "name": "the user gets a response indicating the attempt is right",
  "keyword": "Then "
});
formatter.step({
  "line": 36,
  "name": "the user gets 10 points for the attempt",
  "keyword": "And "
});
formatter.step({
  "line": 37,
  "name": "the user gets the BRONZE_MULTIPLICATOR badge",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "line": 42,
  "name": "The user sends a right attempt after 49 right attempts and then gets a badge SILVER_MULTIPLICATOR",
  "description": "",
  "id": "users-are-able-to-send-their-multiplication;the-user-sends-a-right-attempt-after-\u003cprevious-attempts\u003e-right-attempts-and-then-gets-a-badge-\u003cbadge-name\u003e;;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 33,
  "name": "the user john_snow sends 49 right attempts",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 34,
  "name": "the user john_snow sends 1 right attempts",
  "keyword": "When "
});
formatter.step({
  "line": 35,
  "name": "the user gets a response indicating the attempt is right",
  "keyword": "Then "
});
formatter.step({
  "line": 36,
  "name": "the user gets 10 points for the attempt",
  "keyword": "And "
});
formatter.step({
  "line": 37,
  "name": "the user gets the SILVER_MULTIPLICATOR badge",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "line": 43,
  "name": "The user sends a right attempt after 99 right attempts and then gets a badge GOLD_MULTIPLICATOR",
  "description": "",
  "id": "users-are-able-to-send-their-multiplication;the-user-sends-a-right-attempt-after-\u003cprevious-attempts\u003e-right-attempts-and-then-gets-a-badge-\u003cbadge-name\u003e;;4",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 33,
  "name": "the user john_snow sends 99 right attempts",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 34,
  "name": "the user john_snow sends 1 right attempts",
  "keyword": "When "
});
formatter.step({
  "line": 35,
  "name": "the user gets a response indicating the attempt is right",
  "keyword": "Then "
});
formatter.step({
  "line": 36,
  "name": "the user gets 10 points for the attempt",
  "keyword": "And "
});
formatter.step({
  "line": 37,
  "name": "the user gets the GOLD_MULTIPLICATOR badge",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});