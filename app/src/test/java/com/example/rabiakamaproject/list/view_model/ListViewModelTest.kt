package com.example.rabiakamaproject.list.view_model

import android.content.Context
import com.example.rabiakamaproject.list.model.ProductItemsModel
import com.example.rabiakamaproject.list.model.ProductListModel
import com.example.rabiakamaproject.list.model.ProductSliderModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*

internal class ListViewModelTest {

    private lateinit var viewModel: ListViewModel

    private lateinit var context: Context

    @ExperimentalCoroutinesApi
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        whenever("Error").thenReturn("Unexpected Error")
        viewModel = ListViewModel(context)
    }

    @ExperimentalCoroutinesApi
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ParameterizedTest
    @MethodSource("testGetStartFetchProducts_Data")
    fun testGetStartFetchProducts(
        result: List<ProductItemsModel>?,
        throwable: Throwable?
    ) {
        // When
        viewModel.products

        // Then
        Assert.assertTrue(viewModel.progress.value!!)

        val actual = viewModel.products.value

        Assert.assertFalse(viewModel.progress.value!!)

        if (throwable != null) {
            Assert.assertNull(actual)
            assertEquals(result!!, "Error")
        } else {
            Assert.assertNotNull(result)
        }
    }

    companion object {

        @Suppress("unused")
        @JvmStatic
        fun testGetStartFetchProducts_Data() = listOf(
            Arguments.of(
                listOf(createProducts()), null
            )
        )

        private fun createProducts() = ProductItemsModel().apply {
                this.displayType = "SINGLE"
                this.horizontalList = listOf(ProductSliderModel().apply {
                    this.id = 1
                    this.name = "Test"
                    this.categoryHierarchy = "Test"
                    this.marketPrice = 2.0
                    this.imageUrl = "https://cdn.dsmcdn.com/ty294/product/media/images/20220115/11/26863935/287604966/2/2_org_zoom.jpg"
                })
                this.verticalList = listOf(ProductListModel().apply {
                    this.id = 2
                    this.name = "Test2"
                    this.imageUrl = "https://cdn.dsmcdn.com/ty459/product/media/images/20220623/13/129230106/457940151/2/2_org_zoom.jpg"
                    this.marketPrice = 3.0
                    this.categoryHierarchy = "Test2"
                    this.averageRating = 4.0
                })
        }

    }
}