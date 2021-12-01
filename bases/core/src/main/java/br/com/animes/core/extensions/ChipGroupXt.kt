package br.com.animes.core.extensions

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.getSelectedChipText() = this.findViewById<Chip>(
    checkedChipId
).text.toString()