package com.daffodyl.medialibrary

import kotlinx.serialization.Serializable

class Destinations {
    @Serializable object Notes
    @Serializable class NoteModification(val noteId: Int? = null)
}