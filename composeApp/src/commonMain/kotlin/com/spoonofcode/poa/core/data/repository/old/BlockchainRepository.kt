package com.spoonofcode.poa.core.data.repository.old

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.old.Blockchain

class BlockchainRepository : GenericCrudRepository<Blockchain, Blockchain>(
    resourceName = "blockchain",
    requestSerializer = Blockchain.serializer(),
    responseSerializer = Blockchain.serializer(),
)