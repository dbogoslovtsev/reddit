package com.example.data

import com.example.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private var retrofit: RedditRetrofitService) : MainRepository {
}