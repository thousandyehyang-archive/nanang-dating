package com.nanangdating.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.nanangdating.model.GameStatus;
import com.nanangdating.model.User;
import com.nanangdating.service.GameService;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private GameService gameService = new GameService();

    // GET 요청: 초기 게임 페이지(JSP)를 보여줍니다.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/game.jsp").forward(request, response);
    }

    // POST 요청: 폼 제출 후 게임 로직을 처리합니다.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String characterId = request.getParameter("characterId");
        String userInput = request.getParameter("userInput");

        // 실제 애플리케이션에서는 로그인 정보를 세션에서 가져옵니다.
        User user = new User();
        user.setUsername("anonymous");

        GameStatus status = gameService.processGame(characterId, user, userInput);
        request.setAttribute("gameStatus", status);
        request.getRequestDispatcher("/pages/game.jsp").forward(request, response);
    }
}
