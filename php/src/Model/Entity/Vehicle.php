<?php

declare(strict_types=1);

namespace App\Model\Entity;

use App\Repository\VehicleRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: VehicleRepository::class)]
#[ORM\Table(name: 'vehicle')]
class Vehicle
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: 'IDENTITY')]
    #[ORM\Column(name: 'id', nullable: false)]
    private ?int $id = null;

    #[Assert\NotBlank]
    #[ORM\Column(name: 'name', nullable: false)]
    private ?string $name = null;

    #[Assert\NotNull]
    #[ORM\ManyToOne(targetEntity: VehicleType::class, cascade: ['persist'])]
    #[ORM\JoinColumn(name: 'vehicle_type', referencedColumnName: 'id', nullable: false)]
    private ?VehicleType $vehicleType = null;

    #[Assert\NotNull]
    #[ORM\Column(name: 'registration_date', type: Types::DATETIMETZ_IMMUTABLE, nullable: false)]
    private ?\DateTimeImmutable $registrationDate = null;

    #[Assert\NotNull]
    #[Assert\Positive]
    #[ORM\Column(name: 'weight', nullable: false)]
    private int $weight = 0; // in Tonnen

    #[Assert\NotNull]
    #[ORM\Column(name: 'ready_for_service', nullable: false)]
    private bool $readyForService = false;

    #[Assert\NotNull]
    #[Assert\Positive]
    #[ORM\Column(name: 'number_of_axes', nullable: false)]
    private int $numberOfAxes = 0;

    #[Assert\NotNull]
    #[Assert\Positive]
    #[ORM\Column(name: 'max_speed', nullable: false)]
    private int $maxSpeed = 0; // in km/h

    public function getId(): ?int
    {
        return $this->id;
    }

    public function setId(?int $id): static
    {
        $this->id = $id;

        return $this;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(?string $name): static
    {
        $this->name = $name;

        return $this;
    }

    public function getVehicleType(): ?VehicleType
    {
        return $this->vehicleType;
    }

    public function setVehicleType(?VehicleType $vehicleType): static
    {
        $this->vehicleType = $vehicleType;

        return $this;
    }

    public function getRegistrationDate(): ?\DateTimeImmutable
    {
        return $this->registrationDate;
    }

    public function setRegistrationDate(?\DateTimeImmutable $registrationDate): static
    {
        $this->registrationDate = $registrationDate;

        return $this;
    }

    public function getWeight(): int
    {
        return $this->weight;
    }

    public function setWeight(int $weight): static
    {
        $this->weight = $weight;

        return $this;
    }

    public function isReadyForService(): bool
    {
        return $this->readyForService;
    }

    public function setReadyForService(bool $readyForService): static
    {
        $this->readyForService = $readyForService;

        return $this;
    }

    public function getNumberOfAxes(): int
    {
        return $this->numberOfAxes;
    }

    public function setNumberOfAxes(int $numberOfAxes): static
    {
        $this->numberOfAxes = $numberOfAxes;

        return $this;
    }

    public function getMaxSpeed(): int
    {
        return $this->maxSpeed;
    }

    public function setMaxSpeed(int $maxSpeed): static
    {
        $this->maxSpeed = $maxSpeed;

        return $this;
    }
}
