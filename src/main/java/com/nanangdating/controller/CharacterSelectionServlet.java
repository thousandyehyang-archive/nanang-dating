package com.nanangdating.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.nanangdating.model.CharacterModel;
import com.nanangdating.service.CharacterService;

@WebServlet("/characterSelection")
public class CharacterSelectionServlet extends HttpServlet {
    private CharacterService characterService = new CharacterService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 캐릭터 목록을 서비스에서 가져와 요청 속성에 추가
        List<CharacterModel> characters = characterService.getAvailableCharacters();
        request.setAttribute("characters", characters);
        request.getRequestDispatcher("/pages/characterSelection.jsp").forward(request, response);
    }
}
