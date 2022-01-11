package com.codenjoy.dojo.games.namdreab;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2012 - 2022 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.printer.CharElement;

public enum Element implements CharElement {

/// Board stuff

    NONE(' ',             "Empty space - space where the hero can move."),

    WALL('☼',             "Impenetrable wall."),

    START_FLOOR('#',      "Respawn point from which the hero starts its movement."),

    OTHER('?',            "."),

    APPLE('○',            "Apple."),

    STONE('●',            "Stone."),

    FLYING_PILL('©',      "Flying pill/Angel's wings."),

    FURY_PILL('®',        "Fury pill/Devil's mask."),

    GOLD('$',             "Gold."),

/// голова твоей змеи в разных состояниях и напрвлениях

    HEAD_DOWN('▼',        "Your hero head pointing down."),

    HEAD_LEFT('◄',        "Your hero head pointing left."),

    HEAD_RIGHT('►',       "Your hero head pointing right."),

    HEAD_UP('▲',          "Your hero head pointing up."),

    HEAD_DEAD('☻',        "Your hero is dead."),

    HEAD_EVIL('♥',        "Your hero head under influence Fury pill/Devils mask."),

    HEAD_FLY('♠',         "Your hero head under influence Flying pill/Angels wings."),

    HEAD_SLEEP('&',       "Your hero head when hero is inactive."),

/// туловище твоей змеи

    BODY_HORIZONTAL('═',  "Body of your hero is directed horizontally."),

    BODY_VERTICAL('║',    "Body of your hero is directed vertically."),

    BODY_LEFT_DOWN('╗',   "Turning your hero body from left to down."),

    BODY_LEFT_UP('╝',     "Turning your hero body from left to up."),

    BODY_RIGHT_DOWN('╔',  "Turning your hero body from right to down."),

    BODY_RIGHT_UP('╚',    "Turning your hero body from left to up."),

/// хвост твоей змеи

    TAIL_END_DOWN('╙',    "Your hero tail (end) pointing down."),

    TAIL_END_LEFT('╘',    "Your hero tail (end) pointing left."),

    TAIL_END_UP('╓',      "Your hero tail (end) pointing up."),

    TAIL_END_RIGHT('╕',   "Your hero tail (end) pointing right."),

    TAIL_INACTIVE('~',    "Your hero tail (end) when hero is inactive."),

/// голова змейки противника

    ENEMY_HEAD_DOWN('˅',  "Enemy hero head pointing down."),

    ENEMY_HEAD_LEFT('<',  "Enemy hero head pointing left."),

    ENEMY_HEAD_RIGHT('>', "Enemy hero head pointing right."),

    ENEMY_HEAD_UP('˄',    "Enemy hero head pointing up."),

    ENEMY_HEAD_DEAD('☺',  "Enemy hero is dead."),

    ENEMY_HEAD_EVIL('♣',  "Enemy hero head under influence Fury pill/Devils mask."),

    ENEMY_HEAD_FLY('♦',   "Enemy hero head under influence Flying pill/Angels wings."),

    ENEMY_HEAD_SLEEP('ø', "Enemy hero head when hero is inactive."),

/// туловище змейки противника

    ENEMY_BODY_HORIZONTAL('─', "Body of enemy hero is directed horizontally."),

    ENEMY_BODY_VERTICAL('│',   "Body of enemy hero is directed vertically."),

    ENEMY_BODY_LEFT_DOWN('┐',  "Turning enemy hero body from left to down."),

    ENEMY_BODY_LEFT_UP('┘',    "Turning enemy hero body from left to up."),

    ENEMY_BODY_RIGHT_DOWN('┌', "Turning enemy hero body from right to down."),

    ENEMY_BODY_RIGHT_UP('└',   "Turning enemy hero body from left to up."),

/// хвост змейки противника

    ENEMY_TAIL_END_DOWN('¤',   "Enemy hero tail (end) pointing down."),

    ENEMY_TAIL_END_LEFT('×',   "Enemy hero tail (end) pointing left."),

    ENEMY_TAIL_END_UP('æ',     "Enemy hero tail (end) pointing up."),

    ENEMY_TAIL_END_RIGHT('ö',  "Enemy hero tail (end) pointing right."),

    ENEMY_TAIL_INACTIVE('*',   "Enemy hero tail (end) when hero is inactive.");

    private final char ch;
    private final String info;

    Element(char ch, String info) {
        this.ch = ch;
        this.info = info;
    }

    @Override
    public char ch() {
        return ch;
    }

    @Override
    public String info() {
        return info;
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }

    public static Element valueOf(char ch) {
        for (Element el : Element.values()) {
            if (el.ch == ch) {
                return el;
            }
        }
        throw new IllegalArgumentException("No such element for " + ch);
    }

    public enum BodyDirection {

        HORIZONTAL,
        VERTICAL,
        TURNED_LEFT_UP,
        TURNED_LEFT_DOWN,
        TURNED_RIGHT_UP,
        TURNED_RIGHT_DOWN
    }

    public enum TailDirection {

        HORIZONTAL_RIGHT,
        HORIZONTAL_LEFT,
        VERTICAL_UP,
        VERTICAL_DOWN
    }

    public static Element tail(TailDirection direction, boolean isHero) {
        return isHero
                ? heroTail(direction)
                : enemyTail(direction);
    }

    private static Element heroTail(TailDirection direction) {
        switch (direction) {
            case VERTICAL_DOWN:    return TAIL_END_DOWN;
            case VERTICAL_UP:      return TAIL_END_UP;
            case HORIZONTAL_LEFT:  return TAIL_END_LEFT;
            case HORIZONTAL_RIGHT: return TAIL_END_RIGHT;
            default:               return OTHER;
        }
    }

    private static Element enemyTail(TailDirection direction) {
        switch (direction) {
            case VERTICAL_DOWN:    return ENEMY_TAIL_END_DOWN;
            case VERTICAL_UP:      return ENEMY_TAIL_END_UP;
            case HORIZONTAL_LEFT:  return ENEMY_TAIL_END_LEFT;
            case HORIZONTAL_RIGHT: return ENEMY_TAIL_END_RIGHT;
            default:               return OTHER;
        }
    }

    public static Element head(Direction direction, boolean isHero) {
        return isHero
                ? heroHead(direction)
                : enemyHead(direction);
    }

    private static Element heroHead(Direction direction) {
        switch (direction) {
            case DOWN:  return HEAD_DOWN;
            case UP:    return HEAD_UP;
            case LEFT:  return HEAD_LEFT;
            case RIGHT: return HEAD_RIGHT;
            default:    return OTHER;
        }
    }

    private static Element enemyHead(Direction direction) {
        switch (direction) {
            case DOWN:  return ENEMY_HEAD_DOWN;
            case UP:    return ENEMY_HEAD_UP;
            case LEFT:  return ENEMY_HEAD_LEFT;
            case RIGHT: return ENEMY_HEAD_RIGHT;
            default:    return OTHER;
        }
    }

    public static Element body(BodyDirection direction, boolean isHero) {
        return isHero
                ? heroBody(direction)
                : enemyBody(direction);
    }

    private static Element heroBody(BodyDirection direction) {
        switch (direction) {
            case HORIZONTAL:        return BODY_HORIZONTAL;
            case VERTICAL:          return BODY_VERTICAL;
            case TURNED_LEFT_DOWN:  return BODY_LEFT_DOWN;
            case TURNED_LEFT_UP:    return BODY_LEFT_UP;
            case TURNED_RIGHT_DOWN: return BODY_RIGHT_DOWN;
            case TURNED_RIGHT_UP:   return BODY_RIGHT_UP;
            default:                return OTHER;
        }
    }

    private static Element enemyBody(BodyDirection direction) {
        switch (direction) {
            case HORIZONTAL:        return ENEMY_BODY_HORIZONTAL;
            case VERTICAL:          return ENEMY_BODY_VERTICAL;
            case TURNED_LEFT_DOWN:  return ENEMY_BODY_LEFT_DOWN;
            case TURNED_LEFT_UP:    return ENEMY_BODY_LEFT_UP;
            case TURNED_RIGHT_DOWN: return ENEMY_BODY_RIGHT_DOWN;
            case TURNED_RIGHT_UP:   return ENEMY_BODY_RIGHT_UP;
            default:                return OTHER;
        }
    }

    public Element enemyHero() {
        switch (this) {
            case HEAD_DOWN:       return ENEMY_HEAD_DOWN;
            case HEAD_LEFT:       return ENEMY_HEAD_LEFT;
            case HEAD_RIGHT:      return ENEMY_HEAD_RIGHT;
            case HEAD_UP:         return ENEMY_HEAD_UP;
            case HEAD_DEAD:       return ENEMY_HEAD_DEAD;
            case HEAD_EVIL:       return ENEMY_HEAD_EVIL;
            case HEAD_FLY:        return ENEMY_HEAD_FLY;
            case HEAD_SLEEP:      return ENEMY_HEAD_SLEEP;
            case BODY_HORIZONTAL: return ENEMY_BODY_HORIZONTAL;
            case BODY_VERTICAL:   return ENEMY_BODY_VERTICAL;
            case BODY_LEFT_DOWN:  return ENEMY_BODY_LEFT_DOWN;
            case BODY_LEFT_UP:    return ENEMY_BODY_LEFT_UP;
            case BODY_RIGHT_DOWN: return ENEMY_BODY_RIGHT_DOWN;
            case BODY_RIGHT_UP:   return ENEMY_BODY_RIGHT_UP;
            case TAIL_END_DOWN:   return ENEMY_TAIL_END_DOWN;
            case TAIL_END_LEFT:   return ENEMY_TAIL_END_LEFT;
            case TAIL_END_UP:     return ENEMY_TAIL_END_UP;
            case TAIL_END_RIGHT:  return ENEMY_TAIL_END_RIGHT;
            case TAIL_INACTIVE:   return ENEMY_TAIL_INACTIVE;
        }
        throw new IllegalArgumentException("Bad hero state: " + this);
    }
}