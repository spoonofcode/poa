package com.spoonofcode.poa.core.data.repository.old

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.old.NFTSerie

class NFTSerieRepository : GenericCrudRepository<NFTSerie, NFTSerie>(
    resourceName = "series",
    requestSerializer = NFTSerie.serializer(),
    responseSerializer = NFTSerie.serializer(),
)