package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.Blockchain

class BlockchainRepository : GenericCrudRepository<Blockchain, Blockchain>(
    resourceName = "blockchain",
    requestSerializer = Blockchain.serializer(),
    responseSerializer = Blockchain.serializer(),
)