package com.example.service;

import com.example.entity.Invest;

public interface InvestService {
    String createInvest(Invest invest);

    Invest queryInvest(int id);
}
