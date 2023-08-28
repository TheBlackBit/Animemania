package com.theblackbit.animemania.android.data.external.datasource.response.characterresponse

import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterAttributes
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterData
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.Image
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.toCharacterEntity
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.validImage
import org.junit.Assert
import org.junit.Test

class CharacterDataTest {

    @Test
    fun testCharacterDataToCharacterEntity() {
        val page = 1
        val collectionId = "1"
        val characterData = CharacterData(
            id = "1",
            attributes = CharacterAttributes(
                canonicalName = "Test",
                description = "Description test",
                image = Image(
                    tiny = "test",
                    large = "test",
                    small = "test",
                    medium = "test",
                    original = "test"
                )
            )
        )

        val characterEntity =
            characterData.toCharacterEntity(page = page, collectionId = collectionId)

        Assert.assertEquals(characterEntity.characterId, characterData.id)
        Assert.assertEquals(characterEntity.page, page)
        Assert.assertEquals(characterEntity.collectionId, collectionId)
        Assert.assertEquals(
            characterEntity.imageUrl,
            characterData.attributes?.image!!.validImage()
        )
        Assert.assertEquals(characterEntity.name, characterData.attributes?.canonicalName)
        Assert.assertEquals(characterEntity.overView, characterData.attributes?.description)
    }

    @Test
    fun testCharacterDataToCharacterEntityNullData() {
        val page = 1
        val collectionId = "1"
        val characterData = CharacterData(
            id = null,
            attributes = null
        )

        val characterEntity =
            characterData.toCharacterEntity(page = page, collectionId = collectionId)

        Assert.assertEquals(characterEntity.characterId, "-1")
        Assert.assertEquals(characterEntity.page, page)
        Assert.assertEquals(characterEntity.collectionId, collectionId)
        Assert.assertEquals(characterEntity.imageUrl, "")
        Assert.assertEquals(characterEntity.name, "")
        Assert.assertEquals(characterEntity.overView, "")
    }

    @Test
    fun testCharacterDataToCharacterEntityAttributesNull() {
        val page = 1
        val collectionId = "1"
        val characterData = CharacterData(
            id = null,
            attributes = CharacterAttributes(
                null,
                null,
                null
            )
        )

        val characterEntity =
            characterData.toCharacterEntity(page = page, collectionId = collectionId)

        Assert.assertEquals(characterEntity.characterId, "-1")
        Assert.assertEquals(characterEntity.page, page)
        Assert.assertEquals(characterEntity.collectionId, collectionId)
        Assert.assertEquals(characterEntity.imageUrl, "")
        Assert.assertEquals(characterEntity.name, "")
        Assert.assertEquals(characterEntity.overView, "")
    }

    @Test
    fun testCharacterDataToCharacterEntityImagesAreNull() {
        val page = 1
        val collectionId = "1"
        val characterData = CharacterData(
            id = null,
            attributes = CharacterAttributes(
                "Test",
                Image(
                    null,
                    null,
                    null,
                    null,
                    null
                ),
                "Test"
            )
        )

        val characterEntity =
            characterData.toCharacterEntity(page = page, collectionId = collectionId)

        Assert.assertEquals(characterEntity.imageUrl, "")
    }
}
