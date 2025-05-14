package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.NFTSerie

class NFTSerieRepository : GenericCrudRepository<NFTSerie, NFTSerie>(
    resourceName = "series",
    requestSerializer = NFTSerie.serializer(),
    responseSerializer = NFTSerie.serializer(),
)