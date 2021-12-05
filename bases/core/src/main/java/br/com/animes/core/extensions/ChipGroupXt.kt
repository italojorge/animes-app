package br.com.animes.core.extensions

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.hasChipChecked() = this.findViewById<Chip>(
    checkedChipId
) != null

fun ChipGroup.getCheckedChipText() = this.findViewById<Chip>(
    checkedChipId
).text.toString()
