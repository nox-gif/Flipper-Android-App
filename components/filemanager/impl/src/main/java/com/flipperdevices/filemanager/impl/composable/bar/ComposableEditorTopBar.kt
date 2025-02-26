package com.flipperdevices.filemanager.impl.composable.editor

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.flipperdevices.core.ui.res.R as DesignSystem
import com.flipperdevices.filemanager.impl.R
import com.flipperdevices.filemanager.impl.composable.bar.ComposableEllipsizeStartText

@Composable
fun ComposableEditorTopBar(path: String, onClickSaveButton: () -> Unit) {
    TopAppBar(
        title = {
            ComposableEllipsizeStartText(
                text = path
            )
        },
        actions = {
            IconButton(onClick = onClickSaveButton) {
                Icon(
                    painter = painterResource(
                        DesignSystem.drawable.ic_ok
                    ),
                    contentDescription = stringResource(
                        R.string.filemanager_save_action
                    )
                )
            }
        }
    )
}
