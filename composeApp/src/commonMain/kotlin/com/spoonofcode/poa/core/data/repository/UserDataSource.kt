package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.model.DailyCheckInStatus
import com.spoonofcode.poa.core.model.RewardType
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.feature.profile.domain.model.Profile

interface UserDataSource {
    suspend fun read(userId: String): Result<User>
    suspend fun readUserPartners(userId: String): Result<List<Partner>>
    suspend fun readUserProducts(userId: String): Result<List<Product>>
    suspend fun addProductToUser(productId: String, userId: String): Result<Unit>
    suspend fun deleteProductFromUser(productId: String, userId: String): Result<Unit>
    suspend fun readUserProfile(userId: String): Result<Profile>
    suspend fun readUserProductSeriesIds(userId: String): Result<List<String>>
    suspend fun addRewardToUser(rewardType: RewardType, userId: String): Result<Unit>
    suspend fun getUserRewardsPoints(userId: String): Result<Int>
    suspend fun getUserDailyCheckInStatus(userId: String): Result<DailyCheckInStatus>
}