package com.codenjoy.dojo.games.mollymage;

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

import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;

/**
 * Author: your name
 * <p>
 * This is your AI algorithm for the game.
 * Implement it at your own discretion.
 * Pay attention to {@link YourSolverTest} - there is
 * a test framework for you.
 */
public class YourSolver implements Solver<Board> {

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        if (board.isGameOver()) return "";

        

        return Command.DROP_POTION;
    }

    private boolean isSafePoint(int x, int y, int pasos) {
        int alcancePosion = Properties.POSION_ALCANCE;

        for (int i=x; i<=x+alcancePosion; i++) {
            Point pointObj = new PointImpl(i, y);
            Element currentElement = board.getAt(pointObj);
            if (!board.isPotionAt(pointObj) && currentElement.ch() != ' ') {
                break;
            }
            if (board.isPotionAt(pointObj)) {
                int toExplote = Character.getNumericValue(currentElement.ch());
                if (toExplote >= pasos) {
                    return false;
                }
            }
        }

        for (int i=x; i>=0 && i>=x-alcancePosion; i--) {
            Point pointObj = new PointImpl(i, y);
            Element currentElement = board.getAt(pointObj);
            if (!board.isPotionAt(pointObj) && currentElement.ch() != ' ') {
                break;
            }
            if (board.isPotionAt(pointObj)) {
                int toExplote = Character.getNumericValue(currentElement.ch());
                if (toExplote >= pasos) {
                    return false;
                }
            }
        }

        for (int i=y; i<=y+alcancePosion; i++) {
            Point pointObj = new PointImpl(x, i);
            Element currentElement = board.getAt(pointObj);
            if (!board.isPotionAt(pointObj) && currentElement.ch() != ' ') {
                break;
            }
            if (board.isPotionAt(pointObj)) {
                int toExplote = Character.getNumericValue(currentElement.ch());
                if (toExplote >= pasos) {
                    return false;
                }
            }
        }

        for (int i=y; i >= 0 && i>=y-alcancePosion; i--) {
            Point pointObj = new PointImpl(x, i);
            Element currentElement = board.getAt(pointObj);
            if (!board.isPotionAt(pointObj) && currentElement.ch() != ' ') {
                break;
            }
            if (board.isPotionAt(pointObj)) {
                int toExplote = Character.getNumericValue(currentElement.ch());
                if (toExplote >= pasos) {
                    return false;
                }
            }
        }

        return true;
    }
}