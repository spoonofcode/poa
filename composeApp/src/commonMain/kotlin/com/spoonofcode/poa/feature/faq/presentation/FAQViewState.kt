package com.spoonofcode.poa.feature.faq.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.base.BaseViewState
import com.spoonofcode.poa.feature.faq.domain.model.FAQ

@Immutable
internal data class FAQViewState(
    val faqs: List<FAQ> = emptyList(),
) : BaseViewState()