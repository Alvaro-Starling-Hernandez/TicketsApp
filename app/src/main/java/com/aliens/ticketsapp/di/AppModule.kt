package com.aliens.ticketsapp.di

import android.content.Context
import androidx.room.Room
import com.aliens.ticketsapp.data.TicketsDb
import com.aliens.ticketsapp.data.TiempoDao
import com.aliens.ticketsapp.data.TiempoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideTicketsDb(@ApplicationContext context: Context): TicketsDb {
        return Room.databaseBuilder(
            context,
            TicketsDb::class.java,
            "TicketsDb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun ProvidesTiempoDAO(ticketsDb: TicketsDb): TiempoDao{
        return ticketsDb.tiempoDao
    }

    @Provides
    fun ProvidesTiempoRepository(tiempoDao: TiempoDao): TiempoRepository{
        return TiempoRepository(tiempoDao)
    }
}